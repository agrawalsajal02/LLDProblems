package pubsubsystem.alternate;

public final class SubscriptionCursor {
    private final String topicName;
    private final String subscriberId;
    private long nextOffset;
    private long droppedCount;

    SubscriptionCursor(String topicName, String subscriberId, long nextOffset) {
        this.topicName = topicName;
        this.subscriberId = subscriberId;
        this.nextOffset = nextOffset;
    }

    String getTopicName() {
        return topicName;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    long getNextOffset() {
        return nextOffset;
    }

    void setNextOffset(long nextOffset) {
        this.nextOffset = nextOffset;
    }

    void addDropped(long count) {
        // Intuition: pull consumer late ho jaye toh skipped messages ko metric me preserve karte hain.
        if (count > 0) {
            droppedCount += count;
        }
    }

    @Override
    public String toString() {
        return "SubscriptionCursor{" +
            "topicName='" + topicName + '\'' +
            ", subscriberId='" + subscriberId + '\'' +
            ", nextOffset=" + nextOffset +
            ", droppedCount=" + droppedCount +
            '}';
    }
}
