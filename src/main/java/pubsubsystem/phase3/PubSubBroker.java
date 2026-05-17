package pubsubsystem.phase3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PubSubBroker implements AutoCloseable {
    private final Map<String, Topic> topics;
    private final Map<String, TopicHandler> handlers;
    private final ExecutorService subscriberExecutor;

    public PubSubBroker() {
        this.topics = new ConcurrentHashMap<>();
        this.handlers = new ConcurrentHashMap<>();
        this.subscriberExecutor = Executors.newCachedThreadPool();
    }

    public Topic createTopic(String topicName) {
        // Intuition: broker topic registry rakhta hai, handler us topic ka worker/control plane manage karta hai.
        Topic topic = topics.computeIfAbsent(topicName, Topic::new);
        handlers.computeIfAbsent(topicName, ignored -> new TopicHandler(topic, subscriberExecutor));
        return topic;
    }

    public Set<String> listTopics() {
        return Collections.unmodifiableSet(topics.keySet());
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        // Intuition: public API broker pe simple rahe, subscription details TopicHandler ke andar chhupi rahe.
        TopicHandler handler = requireHandler(topicName);
        handler.subscribe(subscriber);
    }

    public boolean unsubscribe(String topicName, String subscriberId) {
        return requireHandler(topicName).unsubscribe(subscriberId);
    }

    public long publish(String topicName, Message message) {
        // Intuition: publish append-only log me jayega, delivery async workers independently karenge.
        return requireHandler(topicName).publish(message);
    }

    public long publish(String topicName, String payload) {
        return publish(topicName, new Message(payload));
    }

    public boolean resetOffset(String topicName, String subscriberId, long newOffset) {
        // Intuition: offset reset se replay/skip control milta hai, but retained range ke bahar allow nahi karte.
        return requireHandler(topicName).resetOffset(subscriberId, newOffset);
    }

    public List<SubscriptionStats> getSubscriptionStats(String topicName) {
        return requireHandler(topicName).getStats();
    }

    public List<SubscriptionStats> getAllSubscriptionStats() {
        // Intuition: observability ke liye saare handlers se stats collect karna enough hai.
        List<SubscriptionStats> stats = new ArrayList<>();
        for (TopicHandler handler : handlers.values()) {
            stats.addAll(handler.getStats());
        }
        return stats;
    }

    @Override
    public void close() {
        // Intuition: graceful cleanup me pehle subscription workers stop, phir executor shutdown.
        for (TopicHandler handler : handlers.values()) {
            handler.shutdown();
        }
        subscriberExecutor.shutdownNow();
    }

    private TopicHandler requireHandler(String topicName) {
        // Intuition: invalid topic ko fail-fast karo, silent no-op debugging ko mushkil banata hai.
        TopicHandler handler = handlers.get(topicName);
        if (handler == null) {
            throw new IllegalArgumentException("topic does not exist: " + topicName);
        }
        return handler;
    }
}
