package pubsubsystem.phase2;

public final class SubscriberStats {
    private final String subscriberId;
    private final long deliveredCount;
    private final long droppedCount;
    private final int pendingMessages;

    SubscriberStats(String subscriberId, long deliveredCount, long droppedCount, int pendingMessages) {
        this.subscriberId = subscriberId;
        this.deliveredCount = deliveredCount;
        this.droppedCount = droppedCount;
        this.pendingMessages = pendingMessages;
    }

    @Override
    public String toString() {
        return "SubscriberStats{" +
            "subscriberId='" + subscriberId + '\'' +
            ", deliveredCount=" + deliveredCount +
            ", droppedCount=" + droppedCount +
            ", pendingMessages=" + pendingMessages +
            '}';
    }
}
