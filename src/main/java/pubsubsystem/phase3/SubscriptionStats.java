package pubsubsystem.phase3;

public final class SubscriptionStats {
    private final String topicName;
    private final String subscriberId;
    private final long nextOffset;
    private final long deliveredCount;
    private final long droppedCount;
    private final boolean active;

    SubscriptionStats(
        String topicName,
        String subscriberId,
        long nextOffset,
        long deliveredCount,
        long droppedCount,
        boolean active
    ) {
        this.topicName = topicName;
        this.subscriberId = subscriberId;
        this.nextOffset = nextOffset;
        this.deliveredCount = deliveredCount;
        this.droppedCount = droppedCount;
        this.active = active;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    @Override
    public String toString() {
        return "SubscriptionStats{" +
            "topicName='" + topicName + '\'' +
            ", subscriberId='" + subscriberId + '\'' +
            ", nextOffset=" + nextOffset +
            ", deliveredCount=" + deliveredCount +
            ", droppedCount=" + droppedCount +
            ", active=" + active +
            '}';
    }
}
