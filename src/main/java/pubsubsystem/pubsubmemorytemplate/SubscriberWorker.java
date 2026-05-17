package pubsubsystem.pubsubmemorytemplate;

public final class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int currentOffset;
                synchronized (topicSubscriber) {
                    currentOffset = topicSubscriber.getOffset().get();
                    while (currentOffset >= topic.messageCount()) {
                        // Intuition: message nahi hai toh worker wait kare, busy loop CPU waste karega.
                        topicSubscriber.wait();
                        currentOffset = topicSubscriber.getOffset().get();
                    }
                }

                Message message = topic.getMessageAt(currentOffset);
                topicSubscriber.getSubscriber().consume(message);

                // Intuition: resetOffset parallel me ho sakta hai, isliye sirf same offset ho toh increment karo.
                topicSubscriber.getOffset().compareAndSet(currentOffset, currentOffset + 1);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            // Intuition: publish/reset ke baad sleeping worker ko notify karo taaki woh new offset check kare.
            topicSubscriber.notifyAll();
        }
    }
}
