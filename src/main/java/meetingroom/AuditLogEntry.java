package meetingroom;

import java.time.Instant;

public final class AuditLogEntry {
    private final String roomId;
    private final String message;
    private final Instant createdAt;

    public AuditLogEntry(String roomId, String message, Instant createdAt) {
        this.roomId = roomId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
