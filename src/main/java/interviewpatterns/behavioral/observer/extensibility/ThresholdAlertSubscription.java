package interviewpatterns.behavioral.observer.extensibility;

final class ThresholdAlertSubscription {
    private final ThresholdObserver observer;
    private final double threshold;
    private boolean alreadyTriggered;

    ThresholdAlertSubscription(ThresholdObserver observer, double threshold) {
        this.observer = observer;
        this.threshold = threshold;
        this.alreadyTriggered = false;
    }

    ThresholdObserver getObserver() {
        return observer;
    }

    double getThreshold() {
        return threshold;
    }

    boolean isAlreadyTriggered() {
        return alreadyTriggered;
    }

    void markTriggered() {
        alreadyTriggered = true;
    }

    void reset() {
        alreadyTriggered = false;
    }
}
