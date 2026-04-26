package interviewpatterns.behavioral.observer;

public class PriceAlert implements Observer {
    private final double threshold;

    public PriceAlert(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(String symbol, double price) {
        if (price > threshold) {
            System.out.println("Alert: " + symbol + " crossed " + threshold);
        }
    }
}
