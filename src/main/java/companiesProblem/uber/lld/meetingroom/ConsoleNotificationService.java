package companiesProblem.uber.lld.meetingroom;

public final class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyMeetingScheduled(Meeting meeting) {
        System.out.println("Notification sent for " + meeting);
    }
}
