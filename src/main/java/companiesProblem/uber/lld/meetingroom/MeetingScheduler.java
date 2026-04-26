package companiesProblem.uber.lld.meetingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MeetingScheduler {
    private final List<Room> rooms;
    private final RoomSelectionStrategy roomSelectionStrategy;
    private final NotificationService notificationService;

    public MeetingScheduler(
        List<Room> rooms,
        RoomSelectionStrategy roomSelectionStrategy,
        NotificationService notificationService
    ) {
        this.rooms = new ArrayList<>(rooms);
        this.roomSelectionStrategy = roomSelectionStrategy;
        this.notificationService = notificationService;
    }

    public List<Room> findAvailableRooms(TimeSlot requestedSlot) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(requestedSlot)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Optional<Meeting> scheduleMeeting(MeetingRequest request) {
        List<Room> availableRooms = findAvailableRooms(request.getTimeSlot());
        Room room = roomSelectionStrategy.choose(availableRooms);
        if (room == null) {
            return Optional.empty();
        }

        Meeting meeting = room.schedule(request);
        if (meeting == null) {
            return Optional.empty();
        }

        notificationService.notifyMeetingScheduled(meeting);
        return Optional.of(meeting);
    }
}
