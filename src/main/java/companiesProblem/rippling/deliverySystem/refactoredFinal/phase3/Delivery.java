package companiesProblem.rippling.deliverySystem.refactoredFinal.phase3;

import java.time.Duration;
import java.time.LocalDateTime;

public class Delivery {
    int id;
    int driverId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDateTime settledUpToTime;

    public Delivery(int id, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.settledUpToTime = startTime;
    }

    public double getUnsettledCost(LocalDateTime upToTime, double hourlyRate) {
        LocalDateTime effectiveEnd = min(endTime, upToTime);
        if (!effectiveEnd.isAfter(settledUpToTime)) {
            return 0.0;
        }
        double hoursToSettle = Duration.between(settledUpToTime, effectiveEnd).toMillis() / 3_600_000.0;
        return hoursToSettle * hourlyRate;
    }

    public void markSettled(LocalDateTime upToTime) {
        LocalDateTime effectiveEnd = min(endTime, upToTime);
        if (effectiveEnd.isAfter(settledUpToTime)) {
            settledUpToTime = effectiveEnd;
        }
    }

    private LocalDateTime min(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second) ? first : second;
    }
}
