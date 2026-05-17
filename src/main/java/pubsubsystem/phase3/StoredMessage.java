package pubsubsystem.phase3;

final class StoredMessage {
    private final long offset;
    private final Message message;

    StoredMessage(long offset, Message message) {
        this.offset = offset;
        this.message = message;
    }

    long getOffset() {
        return offset;
    }

    Message getMessage() {
        return message;
    }
}
