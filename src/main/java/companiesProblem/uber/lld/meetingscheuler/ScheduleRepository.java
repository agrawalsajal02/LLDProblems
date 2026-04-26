package companiesProblem.uber.lld.meetingscheuler;

import java.util.HashMap;
import java.util.Map;

public final class ScheduleRepository {
    private final Map<String, Schedule> schedulesById = new HashMap<>();

    public void save(Schedule schedule) {
        schedulesById.put(schedule.getScheduleId(), schedule);
    }

    public Schedule findById(String scheduleId) {
        return schedulesById.get(scheduleId);
    }
}
