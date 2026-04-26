package companiesProblem.uber.lld.meetingscheuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class Meeting {
    private final String meetingId;
    private final String scheduleId;
    private final String title;
    private final User organizer;
    private final List<User> participants;
    private final TimeSlot timeSlot;
    private final MeetingRoom room;

    public Meeting(String scheduleId, String title, User organizer, List<User> participants, TimeSlot timeSlot, MeetingRoom room) {
        this.meetingId = UUID.randomUUID().toString();
        this.scheduleId = scheduleId;
        this.title = title;
        this.organizer = organizer;
        this.participants = new ArrayList<>(participants);
        this.timeSlot = timeSlot;
        this.room = room;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getScheduleId() {
        return scheduleId;
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

    public MeetingRoom getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Meeting{"
            + "meetingId='" + meetingId + '\''
            + ", scheduleId='" + scheduleId + '\''
            + ", title='" + title + '\''
            + ", room=" + room.getRoomId()
            + ", timeSlot=" + timeSlot
            + '}';
    }
}
