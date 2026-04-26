package meetingroom;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public final class Main {
    public static void main(String[] args) {
        Room roomA = new Room("A", 4);
        Room roomB = new Room("B", 8);
        Room roomC = new Room("C", 12);

        InMemoryAuditLogger auditLogger = new InMemoryAuditLogger();
        MeetingScheduler scheduler = new MeetingScheduler(
            Arrays.asList(roomA, roomB, roomC),
            new MinSpillageRoomSelectionStrategy(),
            auditLogger,
            new ConsoleNotificationService()
        );

        MeetingRequest firstRequest = new MeetingRequest(
            "host-1",
            new TimeInterval(
                LocalDateTime.of(2026, 3, 31, 10, 0),
                LocalDateTime.of(2026, 3, 31, 11, 0)
            ),
            5,
            Arrays.asList("u1", "u2", "u3")
        );

        System.out.println("Available rooms for first request: " + scheduler.findAvailableRooms(firstRequest));
        Optional<Meeting> firstMeeting = scheduler.scheduleMeeting(firstRequest);
        System.out.println("First booking success? " + firstMeeting.isPresent());

        MeetingRequest secondRequest = new MeetingRequest(
            "host-2",
            new TimeInterval(
                LocalDateTime.of(2026, 3, 31, 10, 30),
                LocalDateTime.of(2026, 3, 31, 11, 30)
            ),
            6,
            Arrays.asList("u4", "u5")
        );

        Optional<Meeting> secondMeeting = scheduler.scheduleMeeting(secondRequest);
        System.out.println("Second booking success? " + secondMeeting.isPresent());

        System.out.println("Room B logs: " + auditLogger.getLogsForRoom("B").size());
        scheduler.purgeAuditLogs(Duration.ofDays(7));
    }
}
