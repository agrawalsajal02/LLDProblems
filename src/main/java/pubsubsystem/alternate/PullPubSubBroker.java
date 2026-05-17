package pubsubsystem.alternate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class PullPubSubBroker {
    private static final int DEFAULT_RETENTION_LIMIT = 1_000;

    private final Map<String, PullTopic> topics = new ConcurrentHashMap<>();

    public void createTopic(String topicName) {
        // Intuition: alternate design me broker sirf log registry hai, delivery threads ki zaroorat nahi.
        topics.computeIfAbsent(topicName, name -> new PullTopic(name, DEFAULT_RETENTION_LIMIT));
    }

    public Set<String> listTopics() {
        return topics.keySet();
    }

    public long publish(String topicName, String payload) {
        return requireTopic(topicName).publish(new Message(payload));
    }

    public SubscriptionCursor subscribe(String topicName, String subscriberId) {
        return requireTopic(topicName).subscribe(subscriberId);
    }

    public List<PublishedMessage> poll(SubscriptionCursor cursor, int maxMessages) {
        // Intuition: consumer apni speed se pull karta hai, broker callback thread manage nahi karta.
        return requireTopic(cursor.getTopicName()).poll(cursor, maxMessages);
    }

    private PullTopic requireTopic(String topicName) {
        PullTopic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("topic does not exist: " + topicName);
        }
        return topic;
    }
}
