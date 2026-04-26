package meetingroom;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Room {
    private final String roomId;
    private final int capacity;
    private final NavigableMap<LocalDateTime, Meeting> bookings;
    private final ReentrantReadWriteLock rwLock;

    public Room(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.bookings = new TreeMap<>();
        this.rwLock = new ReentrantReadWriteLock(true);
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomCandidate inspectCandidate(MeetingRequest request) {
        rwLock.readLock().lock();
        try {
            if (capacity < request.getRequiredCapacity()) {
                return null;
            }
            if (!isAvailableUnderLock(request.getInterval())) {
                return null;
            }
            return new RoomCandidate(this, calculateSpillageMinutesUnderLock(request.getInterval()));
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public Meeting trySchedule(MeetingRequest request) {
        rwLock.writeLock().lock();
        try {
            if (capacity < request.getRequiredCapacity()) {
                return null;
            }
            if (!isAvailableUnderLock(request.getInterval())) {
                return null;
            }

            Meeting meeting = new Meeting(
                UUID.randomUUID().toString(),
                roomId,
                request.getHostUserId(),
                request.getInterval(),
                request.getParticipants(),
                BookingStatus.SCHEDULED
            );
            bookings.put(request.getInterval().getStart(), meeting);
            return meeting;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    private boolean isAvailableUnderLock(TimeInterval requested) {
        Map.Entry<LocalDateTime, Meeting> floor = bookings.floorEntry(requested.getStart());
        if (floor != null && floor.getValue().getInterval().overlaps(requested)) {
            return false;
        }

        Map.Entry<LocalDateTime, Meeting> ceiling = bookings.ceilingEntry(requested.getStart());
        return ceiling == null || !ceiling.getValue().getInterval().overlaps(requested);
    }

    private long calculateSpillageMinutesUnderLock(TimeInterval requested) {
        Map.Entry<LocalDateTime, Meeting> floor = bookings.floorEntry(requested.getStart());
        Map.Entry<LocalDateTime, Meeting> ceiling = bookings.ceilingEntry(requested.getStart());

        LocalDateTime freeSlotStart = floor == null ? requested.getStart() : floor.getValue().getInterval().getEnd();
        LocalDateTime freeSlotEnd = ceiling == null ? requested.getEnd() : ceiling.getValue().getInterval().getStart();

        Duration freeSlotDuration = Duration.between(freeSlotStart, freeSlotEnd);
        Duration requestedDuration = requested.duration();
        Duration spillage = freeSlotDuration.minus(requestedDuration);

        return Math.max(0, spillage.toMinutes());
    }
}
