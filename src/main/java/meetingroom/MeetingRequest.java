package meetingroom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MeetingRequest {
    private final String hostUserId;
    private final TimeInterval interval;
    private final int requiredCapacity;
    private final List<String> participants;

    public MeetingRequest(String hostUserId, TimeInterval interval, int requiredCapacity, List<String> participants) {
        if (hostUserId == null || hostUserId.isEmpty()) {
            throw new IllegalArgumentException("Host user id is required");
        }
        if (requiredCapacity <= 0) {
            throw new IllegalArgumentException("Required capacity must be positive");
        }
        this.hostUserId = hostUserId;
        this.interval = interval;
        this.requiredCapacity = requiredCapacity;
        this.participants = new ArrayList<>(participants);
    }

    public String getHostUserId() {
        return hostUserId;
    }

    public TimeInterval getInterval() {
        return interval;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public List<String> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
