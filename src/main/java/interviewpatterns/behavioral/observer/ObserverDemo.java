package interviewpatterns.behavioral.observer;

public final class ObserverDemo {
    public static void main(String[] args) {
        Stock stock = new Stock("AAPL");
        stock.attach(new PriceDisplay());
        stock.attach(new PriceAlert(150.0));

        stock.setPrice(145.0);
        stock.setPrice(155.0);
    }
}
