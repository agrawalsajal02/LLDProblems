package interviewpatterns.behavioral.observer.extensibility;

import java.util.ArrayList;
import java.util.List;

public final class ThresholdStock {
    private final String symbol;
    private final List<ThresholdAlertSubscription> subscriptions;
    private double price;

    public ThresholdStock(String symbol) {
        this.symbol = symbol;
        this.subscriptions = new ArrayList<>();
    }

    public void subscribe(ThresholdObserver observer, double threshold) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }
        subscriptions.add(new ThresholdAlertSubscription(observer, threshold));
    }

    public void setPrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        notifyRelevantObservers(oldPrice, newPrice);
    }

    private void notifyRelevantObservers(double oldPrice, double newPrice) {
        for (ThresholdAlertSubscription subscription : subscriptions) {
            double threshold = subscription.getThreshold();

            // Hinglish: threshold ek baar cross ho gaya to baar-baar alert nahi bhejna.
            // Jab price wapas threshold ke neeche aayega tabhi reset hoga.
            if (newPrice <= threshold) {
                subscription.reset();
                continue;
            }

            boolean crossedNow = oldPrice <= threshold && newPrice > threshold;
            if (crossedNow && !subscription.isAlreadyTriggered()) {
                subscription.getObserver().onThresholdCrossed(symbol, newPrice, threshold);
                subscription.markTriggered();
            }
        }
    }
}
