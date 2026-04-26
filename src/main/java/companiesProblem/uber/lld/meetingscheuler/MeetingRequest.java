package companiesProblem.uber.lld.meetingscheuler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MeetingRequest {
    private final String title;
    private final User organizer;
    private final List<User> participants;
    private final TimeSlot timeSlot;
    private final int requiredCapacity;
    private final RecurrenceType recurrenceType;
    private final LocalDateTime recurrenceEndTime;

    public MeetingRequest(
        String title,
        User organizer,
        List<User> participants,
        TimeSlot timeSlot,
        int requiredCapacity
    ) {
        this(title, organizer, participants, timeSlot, requiredCapacity, RecurrenceType.ONCE, null);
    }

    public MeetingRequest(
        String title,
        User organizer,
        List<User> participants,
        TimeSlot timeSlot,
        int requiredCapacity,
        RecurrenceType recurrenceType,
        LocalDateTime recurrenceEndTime
    ) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Meeting title is required.");
        }
        if (organizer == null) {
            throw new IllegalArgumentException("Organizer is required.");
        }
        if (timeSlot == null) {
            throw new IllegalArgumentException("Time slot is required.");
        }
        if (requiredCapacity <= 0) {
            throw new IllegalArgumentException("Required capacity must be positive.");
        }
        if (recurrenceType == null) {
            throw new IllegalArgumentException("Recurrence type is required.");
        }
        if (recurrenceType != RecurrenceType.ONCE && recurrenceEndTime == null) {
            throw new IllegalArgumentException("Recurring meeting needs recurrence end time.");
        }
        if (recurrenceEndTime != null && recurrenceEndTime.isBefore(timeSlot.getEnd())) {
            throw new IllegalArgumentException("Recurrence end time cannot be before first meeting end.");
        }
        this.title = title;
        this.organizer = organizer;
        this.participants = new ArrayList<>(participants == null ? List.of() : participants);
        this.timeSlot = timeSlot;
        this.requiredCapacity = requiredCapacity;
        this.recurrenceType = recurrenceType;
        this.recurrenceEndTime = recurrenceEndTime;
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

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public LocalDateTime getRecurrenceEndTime() {
        return recurrenceEndTime;
    }
}
