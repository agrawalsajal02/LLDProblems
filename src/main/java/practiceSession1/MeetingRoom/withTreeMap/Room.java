package practiceSession1.MeetingRoom.withTreeMap;


import companiesProblem.uber.lld.meetingroom.BookingStatus;

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.UUID;

public class Room {
    String roomId;
    TreeMap<LocalDateTime, Meeting> meetingTreeMap;

    public Room() {
        roomId = UUID.randomUUID().toString();
        this.meetingTreeMap = new TreeMap<>();
    }

    public synchronized void scheduleMeeting(MeetingRequest meetingRequest) {
        // // verify if the rrom is still available
        boolean isRoomAvailable = verifyIfRoomIsAvailable(meetingRequest);

        if (!isRoomAvailable) {
            throw new RuntimeException("Not available rooom");
        }

        meetingTreeMap.put(meetingRequest.timeSlot.getStart(), new Meeting("1", roomId, meetingRequest.timeSlot, BookingStatus.SCHEDULED));

    }

    private boolean verifyIfRoomIsAvailable(MeetingRequest meetingRequest) {
        LocalDateTime meetingStart1 = meetingTreeMap.floorKey(meetingRequest.timeSlot.getStart());
        if(meetingStart1!=null ){
            Meeting meetingEnd1 = meetingTreeMap.get(meetingStart1);
            if(meetingEnd1.getTimeSlot().getEnd().isAfter(meetingRequest.timeSlot.getStart())) {
                return false;
            }
        }

        LocalDateTime meetingStart2 = meetingTreeMap.ceilingKey(meetingRequest.timeSlot.getStart());
        if(meetingStart2!=null ){
            if(meetingStart2.isBefore(meetingRequest.timeSlot.getEnd())){
                return false;
            }
        }

        return true;
    }
}
