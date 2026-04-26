package companiesProblem.zluri.DesignMeetingScheduler;

import java.util.List;

public interface NotificationService {
    void onScheduleCreated(String scheduleId, List<String> participants);
}
