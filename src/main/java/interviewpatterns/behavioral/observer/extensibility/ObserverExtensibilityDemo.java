package interviewpatterns.behavioral.observer.extensibility;

public final class ObserverExtensibilityDemo {
    public static void main(String[] args) {
        ThresholdStock stock = new ThresholdStock("AAPL");

        stock.subscribe(new ClientPriceAlert("Alice"), 150.0);
        stock.subscribe(new ClientPriceAlert("Bob"), 160.0);

        stock.setPrice(145.0);
        stock.setPrice(151.0); // only Alice
        stock.setPrice(155.0); // no duplicate for Alice
        stock.setPrice(161.0); // Bob now triggers
        stock.setPrice(149.0); // Alice resets
        stock.setPrice(152.0); // Alice can trigger again
    }
}
