package pubsubsystem.phase3;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Message {
    private final String id;
    private final String key;
    private final String payload;
    private final Map<String, String> headers;
    private final Instant createdAt;

    public Message(String payload) {
        this(null, payload, Collections.emptyMap());
    }

    public Message(String key, String payload, Map<String, String> headers) {
        if (payload == null || payload.isBlank()) {
            throw new IllegalArgumentException("payload cannot be blank");
        }
        this.id = UUID.randomUUID().toString();
        this.key = key;
        this.payload = payload;
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getPayload() {
        return payload;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
            "id='" + id + '\'' +
            ", key='" + key + '\'' +
            ", payload='" + payload + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}
