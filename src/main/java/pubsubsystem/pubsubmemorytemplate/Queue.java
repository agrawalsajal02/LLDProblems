package pubsubsystem.pubsubmemorytemplate;

public final class Queue {
    public Topic createTopic(String topicName) {
        // Intuition: memory version me Queue sirf API layer hai, topic khud workers manage karega.
        return new Topic(topicName);
    }

    public void subscribe(Topic topic, ISubscriber subscriber) {
        // Intuition: subscriber ko raw store nahi karte, TopicSubscriber wrapper offset bhi carry karta hai.
        topic.addSubscriber(new TopicSubscriber(subscriber));
    }

    public void sendMessage(Message message, Topic topic) {
        // Intuition: publish fast rakho, append ke baad alag thread workers ko notify karega.
        topic.addMessage(message);
        new Thread(() -> topic.publish(), "publisher-" + topic.getName()).start();
    }

    public void resetOffset(Topic topic, ISubscriber subscriber, int newOffset) {
        if (newOffset < 0 || newOffset > topic.messageCount()) {
            throw new IllegalArgumentException("invalid offset: " + newOffset);
        }

        for (TopicSubscriber topicSubscriber : topic.getSubscriberListSnapshot()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                // Intuition: offset reset se subscriber old messages replay ya skip kar sakta hai.
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(subscriber.getId() + " offset reset to " + newOffset);
                topic.startSubscriberWorker(topicSubscriber);
                return;
            }
        }
    }
}
