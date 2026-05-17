package pubsubsystem.phase2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PubSubBroker implements AutoCloseable {
    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Topic createTopic(String topicName) {
        // Intuition: topic create hote hi shared executor inject kar dete hain for async workers.
        return topics.computeIfAbsent(topicName, name -> new Topic(name, executorService));
    }

    public Set<String> listTopics() {
        return topics.keySet();
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        requireTopic(topicName).subscribe(subscriber);
    }

    public boolean unsubscribe(String topicName, String subscriberId) {
        return requireTopic(topicName).unsubscribe(subscriberId);
    }

    public long publish(String topicName, String payload) {
        return requireTopic(topicName).publish(new Message(payload));
    }

    public List<SubscriberStats> stats(String topicName) {
        return requireTopic(topicName).stats();
    }

    @Override
    public void close() {
        // Intuition: broker close par pehle topic workers stop, phir executor shutdown.
        for (Topic topic : topics.values()) {
            topic.shutdown();
        }
        executorService.shutdownNow();
    }

    private Topic requireTopic(String topicName) {
        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("topic does not exist: " + topicName);
        }
        return topic;
    }
}
