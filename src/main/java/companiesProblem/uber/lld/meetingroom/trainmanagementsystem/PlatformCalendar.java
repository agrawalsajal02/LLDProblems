package companiesProblem.uber.lld.meetingroom.trainmanagementsystem;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class PlatformCalendar {
    private final Platform platform;
    private final NavigableMap<LocalDateTime, TrainAssignment> assignmentsByStartTime;

    public PlatformCalendar(Platform platform) {
        this.platform = platform;
        this.assignmentsByStartTime = new TreeMap<>();
    }

    public Platform getPlatform() {
        return platform;
    }

    public boolean isAvailable(TimeSlot requestedSlot) {
        Map.Entry<LocalDateTime, TrainAssignment> floor = assignmentsByStartTime.floorEntry(requestedSlot.getStart());
        if (floor != null && floor.getValue().getTimeSlot().overlaps(requestedSlot)) {
            return false;
        }

        Map.Entry<LocalDateTime, TrainAssignment> ceiling = assignmentsByStartTime.ceilingEntry(requestedSlot.getStart());
        return ceiling == null || !ceiling.getValue().getTimeSlot().overlaps(requestedSlot);
    }

    public void addAssignment(TrainAssignment assignment) {
        assignmentsByStartTime.put(assignment.getTimeSlot().getStart(), assignment);
    }

    public TrainAssignment findAssignmentAt(LocalDateTime pointInTime) {
        Map.Entry<LocalDateTime, TrainAssignment> floor = assignmentsByStartTime.floorEntry(pointInTime);
        if (floor == null) {
            return null;
        }

        TrainAssignment assignment = floor.getValue();
        return assignment.getTimeSlot().contains(pointInTime) ? assignment : null;
    }
}
