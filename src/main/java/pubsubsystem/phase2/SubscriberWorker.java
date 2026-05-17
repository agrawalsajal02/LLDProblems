package pubsubsystem.phase2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

final class SubscriberWorker implements Runnable {
    private final String topicName;
    private final Subscriber subscriber;
    private final BlockingQueue<DeliveryTask> queue;
    private final AtomicBoolean running;
    private final AtomicLong deliveredCount;
    private final AtomicLong droppedCount;

    SubscriberWorker(String topicName, Subscriber subscriber, int queueCapacity) {
        this.topicName = topicName;
        this.subscriber = subscriber;
        this.queue = new LinkedBlockingQueue<>(queueCapacity);
        this.running = new AtomicBoolean(true);
        this.deliveredCount = new AtomicLong();
        this.droppedCount = new AtomicLong();
    }

    boolean offer(DeliveryTask task) {
        // Intuition: queue full hai toh block/retry nahi karte, at-most-once me message drop acceptable hai.
        boolean accepted = queue.offer(task);
        if (!accepted) {
            droppedCount.incrementAndGet();
        }
        return accepted;
    }

    void stop() {
        running.set(false);
    }

    SubscriberStats stats() {
        return new SubscriberStats(subscriber.getId(), deliveredCount.get(), droppedCount.get(), queue.size());
    }

    @Override
    public void run() {
        while (running.get() || !queue.isEmpty()) {
            try {
                DeliveryTask task = queue.poll(200, TimeUnit.MILLISECONDS);
                if (task == null) {
                    continue;
                }
                // Intuition: ek subscriber ka ek worker hai, isliye uske liye order naturally maintain hota hai.
                subscriber.consume(topicName, task.getMessage(), task.getOffset());
                deliveredCount.incrementAndGet();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                running.set(false);
            } catch (RuntimeException runtimeException) {
                droppedCount.incrementAndGet();
            }
        }
    }
}
