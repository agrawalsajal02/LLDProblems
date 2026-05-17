package pubsubsystem.phase3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

final class TopicHandler {
    private final Topic topic;
    private final ExecutorService executorService;
    private final Map<String, TopicSubscription> subscriptions;
    private final Map<String, Future<?>> workers;

    TopicHandler(Topic topic, ExecutorService executorService) {
        this.topic = topic;
        this.executorService = executorService;
        this.subscriptions = new ConcurrentHashMap<>();
        this.workers = new ConcurrentHashMap<>();
    }

    void subscribe(Subscriber subscriber) {
        subscriptions.compute(subscriber.getId(), (subscriberId, existingSubscription) -> {
            if (existingSubscription != null && existingSubscription.isActive()) {
                return existingSubscription;
            }

            // Intuition: naye subscriber ko current end offset se start karao, old retained messages by default replay nahi.
            TopicSubscription subscription = new TopicSubscription(
                topic,
                subscriber,
                topic.getNextOffset()
            );
            Future<?> worker = executorService.submit(new SubscriberWorker(subscription));
            workers.put(subscriberId, worker);
            return subscription;
        });
    }

    boolean unsubscribe(String subscriberId) {
        // Intuition: subscription remove + worker cancel dono karna zaroori hai, warna background task latak sakta hai.
        TopicSubscription subscription = subscriptions.remove(subscriberId);
        Future<?> worker = workers.remove(subscriberId);
        if (subscription == null) {
            return false;
        }

        subscription.deactivate();
        topic.signalWorkers();
        if (worker != null) {
            worker.cancel(true);
        }
        return true;
    }

    long publish(Message message) {
        return topic.publish(message);
    }

    boolean resetOffset(String subscriberId, long newOffset) {
        // Intuition: reset sirf active subscription aur retained range ke andar safe hai.
        TopicSubscription subscription = subscriptions.get(subscriberId);
        if (subscription == null || !subscription.isActive()) {
            return false;
        }
        if (newOffset < topic.getBaseOffset() || newOffset > topic.getNextOffset()) {
            return false;
        }

        subscription.advanceTo(newOffset);
        topic.signalWorkers();
        return true;
    }

    List<SubscriptionStats> getStats() {
        // Intuition: each subscription apna offset/delivery/drop state own karta hai.
        List<SubscriptionStats> stats = new ArrayList<>();
        for (TopicSubscription subscription : subscriptions.values()) {
            stats.add(subscription.getStats());
        }
        return stats;
    }

    void shutdown() {
        // Intuition: copy of keys use karo kyunki unsubscribe map ko mutate karega.
        for (String subscriberId : new ArrayList<>(subscriptions.keySet())) {
            unsubscribe(subscriberId);
        }
    }
}
