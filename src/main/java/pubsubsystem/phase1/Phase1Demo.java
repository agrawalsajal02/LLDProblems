package pubsubsystem.phase1;

public final class Phase1Demo {
    private Phase1Demo() {
    }

    public static void main(String[] args) {
        PubSubBroker broker = new PubSubBroker();
        broker.createTopic("orders");

        broker.subscribe("orders", new PrintSubscriber("order-service"));
        broker.subscribe("orders", new PrintSubscriber("dashboard"));

        broker.publish("orders", "order-1-created");
        broker.publish("orders", "order-2-created");
    }
}
