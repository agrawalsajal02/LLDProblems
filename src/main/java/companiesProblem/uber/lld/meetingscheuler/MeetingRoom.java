package companiesProblem.uber.lld.meetingscheuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MeetingRoom {
    private final String roomId;
    private final int capacity;
    private final List<Meeting> meetings;

    public MeetingRoom(String roomId, int capacity) {
        if (roomId == null || roomId.isBlank()) {
            throw new IllegalArgumentException("Room id is required.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.roomId = roomId;
        this.capacity = capacity;
        this.meetings = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean canHost(int requiredCapacity) {
        return capacity >= requiredCapacity;
    }

    public boolean isAvailable(TimeSlot requestedSlot) {
        for (Meeting meeting : meetings) {
            if (meeting.getTimeSlot().overlaps(requestedSlot)) {
                return false;
            }
        }
        return true;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public boolean isAvailableForAll(List<TimeSlot> requestedSlots) {
        for (TimeSlot requestedSlot : requestedSlots) {
            if (!isAvailable(requestedSlot)) {
                return false;
            }
        }
        return true;
    }

    public List<Meeting> getMeetings() {
        return Collections.unmodifiableList(meetings);
    }

    @Override
    public String toString() {
        return "MeetingRoom{"
            + "roomId='" + roomId + '\''
            + ", capacity=" + capacity
            + '}';
    }
}
