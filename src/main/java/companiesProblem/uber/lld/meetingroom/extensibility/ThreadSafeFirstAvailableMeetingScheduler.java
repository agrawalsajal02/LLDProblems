package companiesProblem.uber.lld.meetingroom.extensibility;

import companiesProblem.uber.lld.meetingroom.Meeting;
import companiesProblem.uber.lld.meetingroom.MeetingRequest;
import companiesProblem.uber.lld.meetingroom.NotificationService;
import companiesProblem.uber.lld.meetingroom.TimeSlot;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ThreadSafeFirstAvailableMeetingScheduler {
    private final List<ThreadSafeRoom> rooms;
    private final NotificationService notificationService;

    public ThreadSafeFirstAvailableMeetingScheduler(
        List<ThreadSafeRoom> rooms,
        NotificationService notificationService
    ) {
        this.rooms = new ArrayList<>(rooms);
        this.notificationService = notificationService;
    }

    public List<ThreadSafeRoom> findAvailableRooms(TimeSlot requestedSlot) {
        List<ThreadSafeRoom> availableRooms = new ArrayList<>();
        for (ThreadSafeRoom room : rooms) {
            if (room.isAvailable(requestedSlot)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Optional<Meeting> scheduleMeeting(MeetingRequest request) {
        List<ThreadSafeRoom> availableRooms = findAvailableRooms(request.getTimeSlot());
        for (ThreadSafeRoom room : availableRooms) {
            Meeting meeting = room.trySchedule(request);
            if (meeting != null) {
                notificationService.notifyMeetingScheduled(meeting);
                return Optional.of(meeting);
            }
        }
        return Optional.empty();
    }
}
