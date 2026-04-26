package companiesProblem.uber.lld.meetingroom;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public final class Main {
    public static void main(String[] args) {
        Room roomA = new Room("A");
        Room roomB = new Room("B");
        Room roomC = new Room("C");

        MeetingScheduler scheduler = new MeetingScheduler(
            Arrays.asList(roomA, roomB, roomC),
            new FirstAvailableRoomSelectionStrategy(),
            new ConsoleNotificationService()
        );

        MeetingRequest firstRequest = new MeetingRequest(
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 0),
                LocalDateTime.of(2026, 4, 10, 11, 0)
            )
        );

        Optional<Meeting> firstMeeting = scheduler.scheduleMeeting(firstRequest);
        System.out.println("First meeting booked? " + firstMeeting.isPresent());

        MeetingRequest secondRequest = new MeetingRequest(
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 30),
                LocalDateTime.of(2026, 4, 10, 11, 30)
            )
        );

        Optional<Meeting> secondMeeting = scheduler.scheduleMeeting(secondRequest);
        System.out.println("Second meeting booked? " + secondMeeting.isPresent());

        System.out.println("Available rooms for 12-1:");
        for (Room room : scheduler.findAvailableRooms(
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 12, 0),
                LocalDateTime.of(2026, 4, 10, 13, 0)
            )
        )) {
            System.out.println(room.getRoomId());
        }
    }
}
