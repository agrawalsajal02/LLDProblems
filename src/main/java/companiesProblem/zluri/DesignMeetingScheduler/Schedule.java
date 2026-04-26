package companiesProblem.zluri.DesignMeetingScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Schedule {
    private final String scheduleId;
    private final long firstMeetingStartTime;
    private final long firstMeetingEndTime;
    private final long recurrenceEndTime;
    private final String createdByUserId;
    private final RecurrenceType recurrenceType;
    private final List<String> participants;
    private ScheduleStatus status;

    public Schedule(
        String scheduleId,
        long firstMeetingStartTime,
        long firstMeetingEndTime,
        long recurrenceEndTime,
        String createdByUserId,
        RecurrenceType recurrenceType,
        List<String> participants
    ) {
        this.scheduleId = scheduleId;
        this.firstMeetingStartTime = firstMeetingStartTime;
        this.firstMeetingEndTime = firstMeetingEndTime;
        this.recurrenceEndTime = recurrenceEndTime;
        this.createdByUserId = createdByUserId;
        this.recurrenceType = recurrenceType;
        this.participants = new ArrayList<>(participants);
        this.status = ScheduleStatus.ACTIVE;
    }

    public String getScheduleId() {
        return scheduleId;
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

    public ScheduleStatus getStatus() {
        return status;
    }

    public void markInactive() {
        this.status = ScheduleStatus.INACTIVE;
    }
}
