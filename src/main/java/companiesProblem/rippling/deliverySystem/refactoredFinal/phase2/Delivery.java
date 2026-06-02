package companiesProblem.rippling.deliverySystem.refactoredFinal.phase2;

import java.time.Duration;
import java.time.LocalDateTime;

public class Delivery {
    int id;
    int driverId;
    LocalDateTime startTime;
    LocalDateTime endTime;

    // Phase 2: Track how much of this delivery has been paid for
    LocalDateTime settledUpToTime;

    public Delivery(int id, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.settledUpToTime = startTime; // Nothing settled initially
    }

    // Calculates the unsettled cost portion up to a specific time
    public double getUnsettledCost(LocalDateTime upToTime, double hourlyRate) {
        LocalDateTime effectiveEnd = min(endTime, upToTime);
        if (!effectiveEnd.isAfter(settledUpToTime)) {
            return 0.0; // Already settled
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

    public boolean hasUnsettledTime() {
        return settledUpToTime.isBefore(endTime);
    }

    private LocalDateTime min(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second) ? first : second;
    }
}
