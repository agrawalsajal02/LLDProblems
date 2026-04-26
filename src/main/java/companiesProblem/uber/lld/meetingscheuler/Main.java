package companiesProblem.uber.lld.meetingscheuler;

import java.time.LocalDateTime;
import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        MeetingRoom roomA = new MeetingRoom("A", 4);
        MeetingRoom roomB = new MeetingRoom("B", 8);

        MeetingScheduler scheduler = new MeetingScheduler(
            List.of(roomA, roomB),
            new ConsoleNotificationService()
        );

        User organizer = new User("U1", "Sajal", "sajal@example.com");
        User guest1 = new User("U2", "Aman", "aman@example.com");
        User guest2 = new User("U3", "Priya", "priya@example.com");

        MeetingRequest firstRequest = new MeetingRequest(
            "Design Discussion",
            organizer,
            List.of(guest1, guest2),
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 0),
                LocalDateTime.of(2026, 4, 10, 11, 0)
            ),
            3
        );

        Meeting firstMeeting = scheduler.schedule(firstRequest);
        System.out.println("Booked: " + firstMeeting);

        MeetingRequest secondRequest = new MeetingRequest(
            "Sprint Planning",
            organizer,
            List.of(guest1),
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 30),
                LocalDateTime.of(2026, 4, 10, 11, 30)
            ),
            2
        );

        Meeting secondMeeting = scheduler.schedule(secondRequest);
        System.out.println("Booked: " + secondMeeting);

        MeetingRequest recurringRequest = new MeetingRequest(
            "Daily Standup",
            organizer,
            List.of(guest1, guest2),
            new TimeSlot(
                LocalDateTime.of(2026, 4, 11, 12, 0),
                LocalDateTime.of(2026, 4, 11, 12, 30)
            ),
            3,
            RecurrenceType.DAILY,
            LocalDateTime.of(2026, 4, 13, 12, 30)
        );

        List<Meeting> recurringMeetings = scheduler.createSchedule(recurringRequest);
        System.out.println("Recurring occurrences booked: " + recurringMeetings.size());
    }
}
