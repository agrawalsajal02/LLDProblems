package interviewpatterns.behavioral.observer.extensibility;

public interface ThresholdObserver {
    void onThresholdCrossed(String symbol, double price, double threshold);
}
