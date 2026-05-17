package pubsubsystem.phase2;

public final class Phase2Demo {
    private Phase2Demo() {
    }

    public static void main(String[] args) throws InterruptedException {
        try (PubSubBroker broker = new PubSubBroker()) {
            broker.createTopic("orders");
            broker.subscribe("orders", new PrintSubscriber("slow-order-service", 300));
            broker.subscribe("orders", new PrintSubscriber("fast-dashboard", 0));

            broker.publish("orders", "order-1-created");
            broker.publish("orders", "order-2-created");
            broker.publish("orders", "order-3-created");

            Thread.sleep(1_200);
            System.out.println(broker.stats("orders"));
        }
    }
}
