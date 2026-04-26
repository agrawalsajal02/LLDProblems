package companiesProblem.uber.lld.meetingscheuler;

import java.util.List;

public interface NotificationService {
    void notifyUser(User user, String message);

    void notifyScheduleCreated(Schedule schedule, List<Meeting> meetings);
}
