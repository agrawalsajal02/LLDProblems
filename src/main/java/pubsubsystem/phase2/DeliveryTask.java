package pubsubsystem.phase2;

final class DeliveryTask {
    private final Message message;
    private final long offset;

    DeliveryTask(Message message, long offset) {
        this.message = message;
        this.offset = offset;
    }

    Message getMessage() {
        return message;
    }

    long getOffset() {
        return offset;
    }
}
