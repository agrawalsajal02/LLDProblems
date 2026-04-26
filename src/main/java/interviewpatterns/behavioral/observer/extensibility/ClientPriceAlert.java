package interviewpatterns.behavioral.observer.extensibility;

public final class ClientPriceAlert implements ThresholdObserver {
    private final String clientName;

    public ClientPriceAlert(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void onThresholdCrossed(String symbol, double price, double threshold) {
        System.out.println(
            "Alert for " + clientName + ": " + symbol + " crossed " + threshold + " and is now " + price
        );
    }
}
