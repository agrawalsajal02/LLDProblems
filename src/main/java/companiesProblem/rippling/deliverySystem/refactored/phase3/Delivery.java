package companiesProblem.rippling.deliverySystem.refactored.phase3;

public class Delivery {
    int id;
    int driverId;
    long startTime;
    long endTime;
    long settledUpToTime;

    public Delivery(int id, int driverId, long startTime, long endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.settledUpToTime = startTime;
    }

    public double getUnsettledCost(long upToTime, double hourlyRate) {
        long effectiveEnd = Math.min(endTime, upToTime);
        if (effectiveEnd <= settledUpToTime) {
            return 0.0;
        }
        double hoursToSettle = (effectiveEnd - settledUpToTime) / 3600.0;
        return hoursToSettle * hourlyRate;
    }

    public void markSettled(long upToTime) {
        long effectiveEnd = Math.min(endTime, upToTime);
        if (effectiveEnd > settledUpToTime) {
            settledUpToTime = effectiveEnd;
        }
    }
}
