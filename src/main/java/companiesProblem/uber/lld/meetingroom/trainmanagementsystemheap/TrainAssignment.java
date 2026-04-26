package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

public final class TrainAssignment {
    private final String assignmentId;
    private final Train train;
    private final Platform platform;
    private final TimeSlot timeSlot;

    public TrainAssignment(String assignmentId, Train train, Platform platform, TimeSlot timeSlot) {
        this.assignmentId = assignmentId;
        this.train = train;
        this.platform = platform;
        this.timeSlot = timeSlot;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public Train getTrain() {
        return train;
    }

    public Platform getPlatform() {
        return platform;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "TrainAssignment{"
            + "assignmentId='" + assignmentId + '\''
            + ", train=" + train.getTrainId()
            + ", platform=" + platform.getPlatformId()
            + ", timeSlot=" + timeSlot
            + '}';
    }
}
