package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Optional;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Room roomA = new Room("A");
        Room roomB = new Room("B");
        Room roomC = new Room("C");

        MeetingScheduler scheduler = new MeetingScheduler(
            Arrays.asList(roomA, roomB, roomC),
            new FirstAvailableRoomSelectionStrategy(),
            new ConsoleNotificationService()
        );

        ZoneId localZone = ZoneId.of("Asia/Kolkata");

        MeetingRequest firstRequest = new MeetingRequest(
            new TimeSlot(
                toInstant(2026, 4, 10, 10, 0, localZone),
                toInstant(2026, 4, 10, 11, 0, localZone)
            )
        );

        Optional<Meeting> firstMeeting = scheduler.scheduleMeeting(firstRequest);
        System.out.println("First meeting booked? " + firstMeeting.isPresent());

        MeetingRequest secondRequest = new MeetingRequest(
            new TimeSlot(
                toInstant(2026, 4, 10, 10, 30, localZone),
                toInstant(2026, 4, 10, 11, 30, localZone)
            )
        );

        Optional<Meeting> secondMeeting = scheduler.scheduleMeeting(secondRequest);
        System.out.println("Second meeting booked? " + secondMeeting.isPresent());

        System.out.println("Available rooms for 12-1 IST:");
        for (Room room : scheduler.findAvailableRooms(
            new TimeSlot(
                toInstant(2026, 4, 10, 12, 0, localZone),
                toInstant(2026, 4, 10, 13, 0, localZone)
            )
        )) {
            System.out.println(room.getRoomId());
        }
    }

    private static Instant toInstant(int year, int month, int day, int hour, int minute, ZoneId zoneId) {
        return LocalDateTime.of(year, month, day, hour, minute).atZone(zoneId).toInstant();
    }
}
