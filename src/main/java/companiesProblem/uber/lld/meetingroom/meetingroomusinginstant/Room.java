package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

import java.time.Instant;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.UUID;

public final class Room {
    private final String roomId;
    private final NavigableMap<Instant, Meeting> bookings;

    public Room(String roomId) {
        this.roomId = roomId;
        this.bookings = new TreeMap<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isAvailable(TimeSlot requestedSlot) {
        Map.Entry<Instant, Meeting> floor = bookings.floorEntry(requestedSlot.getStart());
        if (floor != null && floor.getValue().getTimeSlot().overlaps(requestedSlot)) {
            return false;
        }

        Map.Entry<Instant, Meeting> ceiling = bookings.ceilingEntry(requestedSlot.getStart());
        return ceiling == null || !ceiling.getValue().getTimeSlot().overlaps(requestedSlot);
    }

    public Meeting schedule(MeetingRequest request) {
        if (!isAvailable(request.getTimeSlot())) {
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
    }

    public NavigableMap<Instant, Meeting> getBookings() {
        return bookings;
    }
}
