package practiceSession1.MeetingRoom.withTreeMap;

import java.util.List;

public class MeetingScheduler {
    List<Room>roomList;
    public MeetingScheduler(List<Room> roomList) {
        this.roomList= roomList;
    }

    public void scheduleMeeting(MeetingRequest meetingRequest) {
        // shedule meeting

        List<Room>rooms = findAvailableRoom(meetingRequest);

        // check if how many rooms are available
        Room room = rooms.get(0);

        // get the strategy of selecting a room

        // schedule a meeting in that room.
        room.scheduleMeeting(meetingRequest);
    }
}
