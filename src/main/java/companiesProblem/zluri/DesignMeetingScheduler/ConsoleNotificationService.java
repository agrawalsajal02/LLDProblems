package companiesProblem.zluri.DesignMeetingScheduler;

import java.util.List;

public final class ConsoleNotificationService implements NotificationService {
    @Override
    public void onScheduleCreated(String scheduleId, List<String> participants) {
        System.out.println("Schedule created: " + scheduleId + " for users " + participants);
    }
}
