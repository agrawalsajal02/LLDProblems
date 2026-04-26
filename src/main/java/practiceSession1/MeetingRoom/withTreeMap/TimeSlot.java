package practiceSession1.MeetingRoom.withTreeMap;

import java.time.LocalDateTime;

public class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end time are required.");
        }
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean overlaps(TimeSlot other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    @Override
    public String toString() {
        return "TimeSlot{"
                + "start=" + start
                + ", end=" + end
                + '}';
    }
}
