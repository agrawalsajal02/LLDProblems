package companiesProblem.rippling.deliverySystem.Phase1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Phase 1 – Core Totals (Warm-up)
 * --------------------------------
 * Goal:
 *  - Track drivers, record deliveries, show one number: total cost.
 *
 * APIs:
 *   addDriver(int driverId, BigDecimal hourlyRate)
 *   addOrder(int driverId, Instant start, Instant end)
 *   BigDecimal getTotalCost()
 *
 *
 */
public class DeliverySystemPhase1 {

    // --- Domain models ---
    static class Driver {
        int id;
        BigDecimal hourlyRate;

        Driver(int id, BigDecimal rate) {
            if (rate == null || rate.signum() < 0)
                throw new IllegalArgumentException("Hourly rate must be non-negative");
            this.id = id;
            this.hourlyRate = rate.setScale(2, RoundingMode.HALF_UP);
        }
    }

    // --- Core storage ---
    private final Map<Integer, Driver> drivers = new HashMap<>();
    private BigDecimal totalCost = BigDecimal.ZERO;

    // --- Public API ---
    public void addDriver(int driverId, BigDecimal hourlyRate) {
        if (drivers.containsKey(driverId))
            throw new IllegalArgumentException("Driver already exists: " + driverId);
        drivers.put(driverId, new Driver(driverId, hourlyRate));
    }

    public void addOrder(int driverId, Instant start, Instant end) {
        Driver d = drivers.get(driverId);
        if (d == null)
            throw new NoSuchElementException("Driver not found: " + driverId);
        if (start == null || end == null || !end.isAfter(start))
            throw new IllegalArgumentException("Invalid start/end times");

        long seconds = Duration.between(start, end).getSeconds();
        BigDecimal hours = new BigDecimal(seconds)
                .divide(new BigDecimal(3600), 9, RoundingMode.HALF_UP);

        BigDecimal cost = hours.multiply(d.hourlyRate)
                .setScale(2, RoundingMode.HALF_UP);

        totalCost = totalCost.add(cost);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    // --- Demo / quick test ---
    public static void main(String[] args) {
        DeliverySystemPhase1 sys = new DeliverySystemPhase1();

        sys.addDriver(1, new BigDecimal("20.00"));
        sys.addDriver(2, new BigDecimal("30.50"));

        Instant t0 = Instant.now();
        sys.addOrder(1, t0, t0.plus(Duration.ofHours(3)));          // 3h * 20 = 60.00
        sys.addOrder(2, t0.plus(Duration.ofHours(1)), t0.plus(Duration.ofHours(4))); // 3h * 30.5 = 91.50

        System.out.println("Total cost of all deliveries = " + sys.getTotalCost()); // 151.50
    }
}
