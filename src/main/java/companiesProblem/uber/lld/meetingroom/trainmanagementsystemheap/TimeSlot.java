package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.time.LocalDateTime;

public final class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Invalid time slot");
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

    public boolean contains(LocalDateTime pointInTime) {
        return (pointInTime.equals(start) || pointInTime.isAfter(start)) && pointInTime.isBefore(end);
    }

    public boolean overlaps(TimeSlot other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    @Override
    public String toString() {
        return "[" + start + " -> " + end + "]";
    }
}
