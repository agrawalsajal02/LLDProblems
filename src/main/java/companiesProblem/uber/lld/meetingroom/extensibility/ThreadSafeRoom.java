package companiesProblem.uber.lld.meetingroom.extensibility;

import companiesProblem.uber.lld.meetingroom.BookingStatus;
import companiesProblem.uber.lld.meetingroom.Meeting;
import companiesProblem.uber.lld.meetingroom.MeetingRequest;
import companiesProblem.uber.lld.meetingroom.TimeSlot;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class ThreadSafeRoom {
    private final String roomId;
    private final NavigableMap<LocalDateTime, Meeting> bookings;
    private final ReentrantReadWriteLock rwLock;

    public ThreadSafeRoom(String roomId) {
        this.roomId = roomId;
        this.bookings = new TreeMap<>();
        this.rwLock = new ReentrantReadWriteLock(true);
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isAvailable(TimeSlot requestedSlot) {
        rwLock.readLock().lock();
        try {
            return isAvailableUnderLock(requestedSlot);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public Meeting trySchedule(MeetingRequest request) {
        rwLock.writeLock().lock();
        try {
            if (!isAvailableUnderLock(request.getTimeSlot())) {
                return null;
            }

            Meeting meeting = new Meeting(
                UUID.randomUUID().toString(),
                roomId,
                request.getTimeSlot(),
                BookingStatus.SCHEDULED
            );
            bookings.put(request.getTimeSlot().getStart(), meeting);
            return meeting;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    private boolean isAvailableUnderLock(TimeSlot requestedSlot) {
        Map.Entry<LocalDateTime, Meeting> floor = bookings.floorEntry(requestedSlot.getStart());
        if (floor != null && floor.getValue().getTimeSlot().overlaps(requestedSlot)) {
            return false;
        }

        Map.Entry<LocalDateTime, Meeting> ceiling = bookings.ceilingEntry(requestedSlot.getStart());
        return ceiling == null || !ceiling.getValue().getTimeSlot().overlaps(requestedSlot);
    }
}
