package pubsubsystem.alternate;

import java.util.List;

public final class PullPubSubDemo {
    private PullPubSubDemo() {
    }

    public static void main(String[] args) {
        PullPubSubBroker broker = new PullPubSubBroker();
        broker.createTopic("orders");

        SubscriptionCursor dashboardCursor = broker.subscribe("orders", "dashboard");

        broker.publish("orders", "order-1-created");
        broker.publish("orders", "order-2-created");

        List<PublishedMessage> messages = broker.poll(dashboardCursor, 10);
        System.out.println(messages);
        System.out.println(dashboardCursor);
    }
}
