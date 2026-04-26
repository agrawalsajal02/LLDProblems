package companiesProblem.rippling.deliverySystem.refactored.phase2;

public class Delivery {
    int id;
    int driverId;
    long startTime;
    long endTime;

    // Phase 2: Track how much of this delivery has been paid for
    long settledUpToTime;

    public Delivery(int id, int driverId, long startTime, long endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.settledUpToTime = startTime; // Nothing settled initially
    }

    // Calculates the unsettled cost portion up to a specific time
    public double getUnsettledCost(long upToTime, double hourlyRate) {
        long effectiveEnd = Math.min(endTime, upToTime);
        if (effectiveEnd <= settledUpToTime) {
            return 0.0; // Already settled
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
