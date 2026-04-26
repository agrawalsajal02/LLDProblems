package practiceSession1.MeetingRoom.withTreeMap;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    static void main() {
        Room r1= new Room();
        Room r2= new Room();
        Room r3= new Room();

        MeetingScheduler meetingScheduler=new MeetingScheduler(List.of(r1,r2,r3));

        meetingScheduler.scheduleMeeting(new MeetingRequest(
                new TimeSlot(
                        LocalDateTime.of(2026,4,11,10,0,0),
                        LocalDateTime.of(2026,4,11,11,0,0)
                )
        ));
    }
}
