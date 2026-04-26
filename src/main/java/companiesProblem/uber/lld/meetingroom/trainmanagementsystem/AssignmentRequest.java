package companiesProblem.uber.lld.meetingroom.trainmanagementsystem;

public final class AssignmentRequest {
    private final String trainId;
    private final TimeSlot timeSlot;

    public AssignmentRequest(String trainId, TimeSlot timeSlot) {
        this.trainId = trainId;
        this.timeSlot = timeSlot;
    }

    public String getTrainId() {
        return trainId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
