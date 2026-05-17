package pubsubsystem.phase1;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class PubSubBroker {
    private final Map<String, Topic> topics = new ConcurrentHashMap<>();

    public Topic createTopic(String topicName) {
        // Intuition: broker ek registry hai, topic naam se same object dubara milna chahiye.
        return topics.computeIfAbsent(topicName, Topic::new);
    }

    public Set<String> listTopics() {
        return topics.keySet();
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        // Intuition: broker API simple rakho, actual subscription topic ke paas rahegi.
        requireTopic(topicName).subscribe(subscriber);
    }

    public int publish(String topicName, String payload) {
        // Intuition: phase 1 me publish synchronous hai, publisher subscriber completion ka wait karega.
        return requireTopic(topicName).publish(new Message(payload));
    }

    private Topic requireTopic(String topicName) {
        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("topic does not exist: " + topicName);
        }
        return topic;
    }
}
