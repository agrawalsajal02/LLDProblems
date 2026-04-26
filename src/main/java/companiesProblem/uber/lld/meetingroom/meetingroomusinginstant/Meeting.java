package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

public final class Meeting {
    private final String meetingId;
    private final String roomId;
    private final TimeSlot timeSlot;
    private final BookingStatus status;

    public Meeting(String meetingId, String roomId, TimeSlot timeSlot, BookingStatus status) {
        this.meetingId = meetingId;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public BookingStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Meeting{"
            + "meetingId='" + meetingId + '\''
            + ", roomId='" + roomId + '\''
            + ", timeSlot=" + timeSlot
            + ", status=" + status
            + '}';
    }
}
