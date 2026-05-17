package pubsubsystem.phase3;

public final class PubSubDemo {
    private PubSubDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        try (PubSubBroker broker = new PubSubBroker()) {
            broker.createTopic("orders");
            broker.createTopic("notifications");

            Subscriber orderService = new PrintSubscriber("order-service", 250);
            Subscriber emailService = new PrintSubscriber("email-service", 100);
            Subscriber dashboard = new PrintSubscriber("dashboard", 0);

            broker.subscribe("orders", orderService);
            broker.subscribe("orders", dashboard);
            broker.subscribe("notifications", emailService);
            broker.subscribe("notifications", dashboard);

            broker.publish("orders", "order-1-created");
            broker.publish("orders", "order-2-created");
            broker.publish("notifications", "send-order-1-email");

            Thread.sleep(1_000);

            broker.unsubscribe("orders", "dashboard");
            broker.publish("orders", "order-3-created");

            Thread.sleep(1_000);

            System.out.println("Stats:");
            for (SubscriptionStats stats : broker.getAllSubscriptionStats()) {
                System.out.println(stats);
            }
        }
    }
}
