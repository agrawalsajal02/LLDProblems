package pubsubsystem.alternate;

public final class PublishedMessage {
    private final long offset;
    private final Message message;

    PublishedMessage(long offset, Message message) {
        this.offset = offset;
        this.message = message;
    }

    public long getOffset() {
        return offset;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PublishedMessage{" +
            "offset=" + offset +
            ", payload='" + message.getPayload() + '\'' +
            '}';
    }
}
