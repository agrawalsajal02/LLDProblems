package pubsubsystem.pubsubmemorytemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Topic {
    private final String id;
    private final String name;
    private final List<Message> messageList;
    private final List<TopicSubscriber> subscriberList;
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public Topic(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("topic name cannot be blank");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.messageList = new ArrayList<>();
        this.subscriberList = new ArrayList<>();
        this.subscriberWorkers = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public synchronized void addMessage(Message message) {
        // Intuition: topic ek ordered log hai, publish order preserve karne ke liye append synchronized rakho.
        messageList.add(message);
    }

    public synchronized Message getMessageAt(int offset) {
        // Intuition: worker offset se direct message pick karega, har subscriber ka apna offset hota hai.
        return messageList.get(offset);
    }

    public synchronized int messageCount() {
        // Intuition: size shared hai, lock ke andar read karna safer hai.
        return messageList.size();
    }

    public synchronized void addSubscriber(TopicSubscriber topicSubscriber) {
        // Intuition: topic ke paas subscriber wrappers rahenge, wrapper me offset bhi stored hai.
        subscriberList.add(topicSubscriber);
    }

    public void publish() {
        // Intuition: message add hone ke baad topic apne saare subscriber workers ko wake karega.
        for (TopicSubscriber topicSubscriber : getSubscriberListSnapshot()) {
            startSubscriberWorker(topicSubscriber);
        }
    }

    public void startSubscriberWorker(TopicSubscriber topicSubscriber) {
        String subscriberId = topicSubscriber.getSubscriber().getId();

     // lazy li creating subscriber worker if already not there
        subscriberWorkers.computeIfAbsent(subscriberId, ignored -> {
            // Intuition: ek subscriber ka ek worker rakho, us subscriber ka order naturally maintain hota hai.
            SubscriberWorker worker = new SubscriberWorker(this, topicSubscriber);
            Thread thread = new Thread(worker, "subscriber-worker-" + subscriberId);
            thread.setDaemon(true);
            thread.start();
            return worker;
        });

        // wake up the worker that slept
        subscriberWorkers.get(subscriberId).wakeUpIfNeeded();
    }

    public synchronized List<TopicSubscriber> getSubscriberListSnapshot() {
        // Intuition: snapshot se iteration safe hoti hai jab subscribe parallel me ho sakta hai.
        return new ArrayList<>(subscriberList);
    }
}
