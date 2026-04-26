package companiesProblem.uber.lld.meetingscheuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MeetingRepository {
    private final Map<String, List<Meeting>> meetingsByScheduleId = new HashMap<>();

    public void saveAll(String scheduleId, List<Meeting> meetings) {
        meetingsByScheduleId.put(scheduleId, new ArrayList<>(meetings));
    }

    public List<Meeting> findByScheduleId(String scheduleId) {
        return Collections.unmodifiableList(meetingsByScheduleId.getOrDefault(scheduleId, List.of()));
    }
}
