package pubsubsystem.phase2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public final class Topic {
    private static final int DEFAULT_SUBSCRIBER_QUEUE_CAPACITY = 100;

    private final String name;
    private final ExecutorService executorService;
    private final AtomicLong nextOffset;
    private final Map<String, SubscriberWorker> workers;
    private final Map<String, Future<?>> workerFutures;

    public Topic(String name, ExecutorService executorService) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("topic name cannot be blank");
        }
        this.name = name;
        this.executorService = executorService;
        this.nextOffset = new AtomicLong();
        this.workers = new ConcurrentHashMap<>();
        this.workerFutures = new ConcurrentHashMap<>();
    }

    public void subscribe(Subscriber subscriber) {
        workers.computeIfAbsent(subscriber.getId(), ignored -> {
            // Intuition: har subscription ka dedicated worker slow subscriber ko isolate karta hai.
            SubscriberWorker worker = new SubscriberWorker(name, subscriber, DEFAULT_SUBSCRIBER_QUEUE_CAPACITY);
            workerFutures.put(subscriber.getId(), executorService.submit(worker));
            return worker;
        });
    }

    public boolean unsubscribe(String subscriberId) {
        // Intuition: unsubscribe me worker ko stop signal do aur future cancel karke wait state tod do.
        SubscriberWorker worker = workers.remove(subscriberId);
        Future<?> future = workerFutures.remove(subscriberId);
        if (worker == null) {
            return false;
        }
        worker.stop();
        if (future != null) {
            future.cancel(true);
        }
        return true;
    }

    public long publish(Message message) {
        // Intuition: publisher sirf queues me enqueue karta hai, consume async background me hota hai.
        long offset = nextOffset.getAndIncrement();
        DeliveryTask task = new DeliveryTask(message, offset);
        for (SubscriberWorker worker : workers.values()) {
            worker.offer(task);
        }
        return offset;
    }

    public List<SubscriberStats> stats() {
        List<SubscriberStats> result = new ArrayList<>();
        for (SubscriberWorker worker : workers.values()) {
            result.add(worker.stats());
        }
        return result;
    }

    public void shutdown() {
        for (String subscriberId : new ArrayList<>(workers.keySet())) {
            unsubscribe(subscriberId);
        }
    }
}
