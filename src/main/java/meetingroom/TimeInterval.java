package meetingroom;

import java.time.Duration;
import java.time.LocalDateTime;

public final class TimeInterval implements Comparable<TimeInterval> {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Invalid time interval");
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

    public Duration duration() {
        return Duration.between(start, end);
    }

    public boolean overlaps(TimeInterval other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    @Override
    public int compareTo(TimeInterval other) {
        return this.start.compareTo(other.start);
    }

    @Override
    public String toString() {
        return "[" + start + " -> " + end + "]";
    }
}
