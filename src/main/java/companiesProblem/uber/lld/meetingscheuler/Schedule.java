package companiesProblem.uber.lld.meetingscheuler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class Schedule {
    private final String scheduleId;
    private final String title;
    private final User organizer;
    private final List<User> participants;
    private final int requiredCapacity;
    private final TimeSlot firstTimeSlot;
    private final RecurrenceType recurrenceType;
    private final LocalDateTime recurrenceEndTime;
    private ScheduleStatus status;

    public Schedule(
        String title,
        User organizer,
        List<User> participants,
        int requiredCapacity,
        TimeSlot firstTimeSlot,
        RecurrenceType recurrenceType,
        LocalDateTime recurrenceEndTime
    ) {
        this.scheduleId = UUID.randomUUID().toString();
        this.title = title;
        this.organizer = organizer;
        this.participants = new ArrayList<>(participants);
        this.requiredCapacity = requiredCapacity;
        this.firstTimeSlot = firstTimeSlot;
        this.recurrenceType = recurrenceType;
        this.recurrenceEndTime = recurrenceEndTime;
        this.status = ScheduleStatus.ACTIVE;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getTitle() {
        return title;
    }

    public User getOrganizer() {
        return organizer;
    }

    public List<User> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public TimeSlot getFirstTimeSlot() {
        return firstTimeSlot;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public LocalDateTime getRecurrenceEndTime() {
        return recurrenceEndTime;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void markInactive() {
        status = ScheduleStatus.INACTIVE;
    }
}
