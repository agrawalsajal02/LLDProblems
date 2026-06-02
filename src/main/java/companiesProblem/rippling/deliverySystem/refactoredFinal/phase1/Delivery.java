package companiesProblem.rippling.deliverySystem.refactoredFinal.phase1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Delivery {
    int id;
    int driverId;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Delivery(int id, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public double getDurationInHours() {
        if (!endTime.isAfter(startTime))
            return 0;
        return Duration.between(startTime, endTime).toMillis() / 3_600_000.0;
    }
}
