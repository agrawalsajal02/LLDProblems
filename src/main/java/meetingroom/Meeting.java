package meetingroom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Meeting {
    private final String meetingId;
    private final String roomId;
    private final String hostUserId;
    private final TimeInterval interval;
    private final List<String> participants;
    private final BookingStatus status;

    public Meeting(
        String meetingId,
        String roomId,
        String hostUserId,
        TimeInterval interval,
        List<String> participants,
        BookingStatus status
    ) {
        this.meetingId = meetingId;
        this.roomId = roomId;
        this.hostUserId = hostUserId;
        this.interval = interval;
        this.participants = new ArrayList<>(participants);
        this.status = status;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getHostUserId() {
        return hostUserId;
    }

    public TimeInterval getInterval() {
        return interval;
    }

    public List<String> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public BookingStatus getStatus() {
        return status;
    }
}
