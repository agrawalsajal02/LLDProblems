package companiesProblem.rippling.deliverySystem.Phase3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Phase 3 — Alternate API Surface (Phone-screen variant)
 * ------------------------------------------------------
 * Builds on Phase 1/2 with:
 *   - BigDecimal getDriverHourlyRate(int driverId)
 *   - void recordDelivery(int driverId, Duration duration)  // ends at now
 *   - BigDecimal calculateTotalCost()                       // alias
 *
 * Retains completed-only settlement semantics from prior step:
 *   payUpToTime(t) settles ONLY deliveries whose end <= t.
 */
public class DeliverySystemPhase3 {

    // ===== Domain =====
    static final class Driver {
        final int id;
        final BigDecimal hourlyRate; // snapshot for future orders

        Driver(int id, BigDecimal rate) {
            if (rate == null || rate.signum() < 0) {
                throw new IllegalArgumentException("Hourly rate must be non-negative");
            }
            this.id = id;
            this.hourlyRate = rate.setScale(2, RoundingMode.HALF_UP);
        }
    }

    static final class Order {
        final int driverId;
        final Instant start;
        final Instant end;
        final BigDecimal ratePerHourSnapshot;

        Instant settledUntil; // for idempotent settlements

        Order(int driverId, Instant start, Instant end, BigDecimal ratePerHourSnapshot) {
            if (start == null || end == null || !end.isAfter(start)) {
                throw new IllegalArgumentException("Invalid start/end times");
            }
            if (ratePerHourSnapshot == null || ratePerHourSnapshot.signum() < 0) {
                throw new IllegalArgumentException("Rate must be non-negative");
            }
            this.driverId = driverId;
            this.start = start;
            this.end = end;
            this.ratePerHourSnapshot = ratePerHourSnapshot;
            this.settledUntil = start;
        }

        BigDecimal totalCost() {
            return costBetween(start, end);
        }

        BigDecimal unsettledCostIfCompletedBy(Instant t) {
            if (end.isAfter(t)) return BigDecimal.ZERO;
            if (!end.isAfter(settledUntil)) return BigDecimal.ZERO;
            return costBetween(settledUntil, end);
        }

        void settleFullyIfCompletedBy(Instant t) {
            if (!end.isAfter(t)) {
                if (end.isAfter(settledUntil)) {
                    settledUntil = end;
                }
            }
        }

        private BigDecimal costBetween(Instant a, Instant b) {
            if (!b.isAfter(a)) return BigDecimal.ZERO;
            long seconds = Duration.between(a, b).getSeconds();
            BigDecimal hours = new BigDecimal(seconds)
                    .divide(new BigDecimal(3600), 9, RoundingMode.HALF_UP);
            return hours.multiply(ratePerHourSnapshot).setScale(2, RoundingMode.HALF_UP);
        }
    }

    // ===== State =====
    private final Map<Integer, Driver> drivers = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();

    private BigDecimal totalAccrued = BigDecimal.ZERO; // O(1) reads
    private BigDecimal totalPaid    = BigDecimal.ZERO; // O(1) reads

    // ===== Phase 1 APIs =====
    public void addDriver(int driverId, BigDecimal hourlyRate) {
        if (drivers.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver already exists: " + driverId);
        }
        drivers.put(driverId, new Driver(driverId, hourlyRate));
    }

    public void addOrder(int driverId, Instant start, Instant end) {
        Driver d = drivers.get(driverId);
        if (d == null) throw new NoSuchElementException("Driver not found: " + driverId);
        Order o = new Order(driverId, start, end, d.hourlyRate);
        orders.add(o);
        totalAccrued = totalAccrued.add(o.totalCost());
    }

    public BigDecimal getTotalCost() { return totalAccrued; }

    // ===== Phase 2 APIs (completed-only settlement) =====
    public void payUpToTime(Instant upTo) {
        if (upTo == null) throw new IllegalArgumentException("upTo cannot be null");
        BigDecimal delta = BigDecimal.ZERO;
        for (Order o : orders) {
            BigDecimal add = o.unsettledCostIfCompletedBy(upTo);
            if (add.signum() > 0) {
                o.settleFullyIfCompletedBy(upTo);
                delta = delta.add(add);
            }
        }
        totalPaid = totalPaid.add(delta);
    }

    public BigDecimal getCostToBePaid() {
        BigDecimal remaining = totalAccrued.subtract(totalPaid);
        return remaining.signum() < 0
                ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : remaining;
    }

    // ===== Phase 3 — New APIs =====

    /** Returns the driver's current hourly rate. */
    public BigDecimal getDriverHourlyRate(int driverId) {
        Driver d = drivers.get(driverId);
        if (d == null) throw new NoSuchElementException("Driver not found: " + driverId);
        return d.hourlyRate; // already scale(2)
    }

    /**
     * Records a completed delivery by duration that ends at Instant.now().
     * Example: recordDelivery(7, Duration.ofMinutes(45))
     */
    public void recordDelivery(int driverId, Duration duration) {
        if (duration == null || duration.isZero() || duration.isNegative()) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        Instant end = Instant.now();
        Instant start = end.minus(duration);
        addOrder(driverId, start, end);
    }

    /** Alias to match phone-screen wording. */
    public BigDecimal calculateTotalCost() { return getTotalCost(); }

    // ===== Demo / quick test =====
    public static void main(String[] args) throws InterruptedException {
        DeliverySystemPhase3 sys = new DeliverySystemPhase3();

        // Add one driver at ₹500/h
        sys.addDriver(42, new BigDecimal("500.00"));

        // Test idea: 45m + 30m = 1.25h * 500 = 625.00 total
        // But the prompt example said 0.75h * 500 = 375.00 — that's for a single 45m + 0m.
        // We'll demonstrate both.

        // Single 45m
        sys.recordDelivery(42, Duration.ofMinutes(45));
        System.out.println("After 45m: total = " + sys.calculateTotalCost()); // 0.75h * 500 = 375.00

        // Another 30m
        sys.recordDelivery(42, Duration.ofMinutes(30));
        System.out.println("After +30m: total = " + sys.calculateTotalCost()); // 1.25h * 500 = 625.00

        // Completed-only settlement examples
        Instant now = Instant.now();
        // Both deliveries ended "now" at their creation time; settle all by paying up to 'now'
        sys.payUpToTime(now);
        System.out.println("Unpaid after settle(now): " + sys.getCostToBePaid()); // 0.00

        // If we add a future order and settle before it ends, nothing gets paid (completed-only)
        Instant startFuture = now.plus(Duration.ofMinutes(10));
        Instant endFuture   = now.plus(Duration.ofMinutes(40));
        sys.addOrder(42, startFuture, endFuture); // +0.5h * 500 = 250.00 accrued
        System.out.println("Total after future order: " + sys.getTotalCost()); // 875.00

        sys.payUpToTime(now.plus(Duration.ofMinutes(20))); // still not completed (end at +40m)
        System.out.println("Unpaid after early settle: " + sys.getCostToBePaid()); // 250.00
    }
}
