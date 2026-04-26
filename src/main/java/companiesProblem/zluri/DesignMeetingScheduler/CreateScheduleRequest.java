package companiesProblem.zluri.DesignMeetingScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CreateScheduleRequest {
    private final long firstMeetingStartTime;
    private final long firstMeetingEndTime;
    private final long recurrenceEndTime;
    private final String createdByUserId;
    private final RecurrenceType recurrenceType;
    private final List<String> participants;

    public CreateScheduleRequest(
        long firstMeetingStartTime,
        long firstMeetingEndTime,
        long recurrenceEndTime,
        String createdByUserId,
        RecurrenceType recurrenceType,
        List<String> participants
    ) {
        this.firstMeetingStartTime = firstMeetingStartTime;
        this.firstMeetingEndTime = firstMeetingEndTime;
        this.recurrenceEndTime = recurrenceEndTime;
        this.createdByUserId = createdByUserId;
        this.recurrenceType = recurrenceType;
        this.participants = new ArrayList<>(participants);
    }

    public long getFirstMeetingStartTime() {
        return firstMeetingStartTime;
    }

    public long getFirstMeetingEndTime() {
        return firstMeetingEndTime;
    }

    public long getRecurrenceEndTime() {
        return recurrenceEndTime;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public List<String> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
