package companiesProblem.rippling.deliverySystem.refactored.phase1;

public class Delivery {
    int id;
    int driverId;
    long startTime; // in seconds
    long endTime; // in seconds

    public Delivery(int id, int driverId, long startTime, long endTime) {
        this.id = id;
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public double getDurationInHours() {
        if (endTime <= startTime)
            return 0;
        return (endTime - startTime) / 3600.0;
    }
}
