package pubsubsystem.phase3;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

final class TopicSubscription {
    private final Topic topic;
    private final Subscriber subscriber;
    private final AtomicLong nextOffset;
    private final AtomicLong deliveredCount;
    private final AtomicLong droppedCount;
    private final AtomicBoolean active;

    TopicSubscription(Topic topic, Subscriber subscriber, long startOffset) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.nextOffset = new AtomicLong(startOffset);
        this.deliveredCount = new AtomicLong();
        this.droppedCount = new AtomicLong();
        this.active = new AtomicBoolean(true);
    }

    Topic getTopic() {
        return topic;
    }

    Subscriber getSubscriber() {
        return subscriber;
    }

    long getNextOffset() {
        return nextOffset.get();
    }

    void advanceTo(long offset) {
        nextOffset.set(offset);
    }

    long claimCurrentOffset() {
        // Intuition: offset pehle advance karne se crash/failure duplicate delivery nahi banata.
        return nextOffset.getAndIncrement();
    }

    void markDelivered() {
        deliveredCount.incrementAndGet();
    }

    void markDropped(long count) {
        // Intuition: drops ko metric me count karna slow-consumer debugging ke liye useful hai.
        if (count > 0) {
            droppedCount.addAndGet(count);
        }
    }

    boolean isActive() {
        return active.get();
    }

    void deactivate() {
        active.set(false);
    }

    SubscriptionStats getStats() {
        return new SubscriptionStats(
            topic.getName(),
            subscriber.getId(),
            nextOffset.get(),
            deliveredCount.get(),
            droppedCount.get(),
            active.get()
        );
    }
}
