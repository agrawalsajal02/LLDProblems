package companiesProblem.uber.lld.meetingscheuler;

import java.util.List;

public final class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Notification to " + user.getEmail() + ": " + message);
    }

    @Override
    public void notifyScheduleCreated(Schedule schedule, List<Meeting> meetings) {
        System.out.println(
            "Schedule created: " + schedule.getScheduleId()
                + ", type=" + schedule.getRecurrenceType()
                + ", occurrences=" + meetings.size()
        );
    }
}
