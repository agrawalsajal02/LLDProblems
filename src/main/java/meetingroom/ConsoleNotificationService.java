package meetingroom;

public final class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyMeetingScheduled(Meeting meeting) {
        for (String participant : meeting.getParticipants()) {
            System.out.println(
                "Notification to " + participant
                    + ": meeting "
                    + meeting.getMeetingId()
                    + " scheduled in room "
                    + meeting.getRoomId()
                    + " for interval "
                    + meeting.getInterval()
            );
        }
    }
}
