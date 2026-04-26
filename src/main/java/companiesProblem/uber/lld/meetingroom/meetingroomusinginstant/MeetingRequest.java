package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

public final class MeetingRequest {
    private final TimeSlot timeSlot;

    public MeetingRequest(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
