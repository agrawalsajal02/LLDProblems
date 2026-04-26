package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

import java.time.Duration;
import java.time.Instant;

public final class TimeSlot {
    private final Instant start;
    private final Instant end;

    public TimeSlot(Instant start, Instant end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Invalid meeting slot");
        }
        this.start = start;
        this.end = end;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Duration duration() {
        return Duration.between(start, end);
    }

    public boolean overlaps(TimeSlot other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    @Override
    public String toString() {
        return "[" + start + " -> " + end + "]";
    }
}
