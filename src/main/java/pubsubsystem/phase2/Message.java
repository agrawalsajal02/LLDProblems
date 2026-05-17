package pubsubsystem.phase2;

import java.time.Instant;
import java.util.UUID;

public final class Message {
    private final String id;
    private final String payload;
    private final Instant createdAt;

    public Message(String payload) {
        if (payload == null || payload.isBlank()) {
            throw new IllegalArgumentException("payload cannot be blank");
        }
        this.id = UUID.randomUUID().toString();
        this.payload = payload;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
