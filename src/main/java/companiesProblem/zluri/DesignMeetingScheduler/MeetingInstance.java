package companiesProblem.zluri.DesignMeetingScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MeetingInstance {
    private final String meetingId;
    private final String scheduleId;
    private final String createdByUserId;
    private final List<String> participants;
    private final long startTime;
    private final long endTime;
    private final long dayEpochMillis;
    private ScheduleStatus status;

    public MeetingInstance(
        String meetingId,
        String scheduleId,
        String createdByUserId,
        List<String> participants,
        long startTime,
        long endTime,
        long dayEpochMillis
    ) {
        this.meetingId = meetingId;
        this.scheduleId = scheduleId;
        this.createdByUserId = createdByUserId;
        this.participants = new ArrayList<>(participants);
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayEpochMillis = dayEpochMillis;
        this.status = ScheduleStatus.ACTIVE;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public List<String> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDayEpochMillis() {
        return dayEpochMillis;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void markInactive() {
        this.status = ScheduleStatus.INACTIVE;
    }

    public boolean overlaps(long otherStartTime, long otherEndTime) {
        return otherStartTime < endTime && startTime < otherEndTime;
    }
}
