package companiesProblem.rippling.deliverySystem.Phase2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Phase 2 – Payments (Settlement), O(1) Queries
 * ---------------------------------------------
 * New APIs:
 *  - void payUpToTime(Instant upTo)         // settle prorated cost up to 'upTo'
 *  - BigDecimal getCostToBePaid()           // totalAccrued - totalPaid (>= 0)
 *
 * Data tweak:
 *  - Each Order tracks 'settledUntil' (start initially); we only settle deltas.
 */
public class DeliverySystemPhase2 {

    // --- Domain models ---
    static class Driver {
        final int id;
        final BigDecimal hourlyRate; // snapshot used when adding orders

        Driver(int id, BigDecimal rate) {
            if (rate == null || rate.signum() < 0)
                throw new IllegalArgumentException("Hourly rate must be non-negative");
            this.id = id;
            // keep two-decimal currency scale at the boundary
            this.hourlyRate = rate.setScale(2, RoundingMode.HALF_UP);
        }
    }

    static class Order {
        final int driverId;
        final Instant start;
        final Instant end;
        final BigDecimal ratePerHourSnapshot;

        Instant settledUntil; // in [start, end]; monotonically non-decreasing

        Order(int driverId, Instant start, Instant end, BigDecimal rateSnapshot) {
            if (start == null || end == null || !end.isAfter(start))
                throw new IllegalArgumentException("Invalid start/end times");
            if (rateSnapshot == null || rateSnapshot.signum() < 0)
                throw new IllegalArgumentException("Rate must be non-negative");
            this.driverId = driverId;
            this.start = start;
            this.end = end;
            this.ratePerHourSnapshot = rateSnapshot;
            this.settledUntil = start; // nothing paid yet
        }

        BigDecimal totalCost() {
            return costBetween(start, end);
        }

        BigDecimal unsettledCostUpTo(Instant upTo) {
            Instant right = min(end, upTo);
            if (!right.isAfter(settledUntil)) return BigDecimal.ZERO;
            return costBetween(settledUntil, right);
        }

        void settleUntil(Instant upTo) {
            Instant right = min(end, upTo);
            if (right.isAfter(settledUntil)) settledUntil = right;
        }

        private BigDecimal costBetween(Instant a, Instant b) {
            if (!b.isAfter(a)) return BigDecimal.ZERO;
            long seconds = Duration.between(a, b).getSeconds();
            // High precision fractional hours; round final money to 2 decimals.
            BigDecimal hours = new BigDecimal(seconds)
                    .divide(new BigDecimal(3600), 9, RoundingMode.HALF_UP);
            return hours.multiply(ratePerHourSnapshot).setScale(2, RoundingMode.HALF_UP);
        }

        private static Instant min(Instant a, Instant b) { return a.isBefore(b) ? a : b; }
    }

    // --- Core storage ---
    private final Map<Integer, Driver> drivers = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();

    // Precomputed totals for O(1) queries
    private BigDecimal totalAccrued = BigDecimal.ZERO; // sum of all orders' totalCost
    private BigDecimal totalPaid = BigDecimal.ZERO;    // sum settled via payUpToTime

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

        Order o = new Order(driverId, start, end, d.hourlyRate);
        orders.add(o);

        // Precompute onto totalAccrued for O(1) getTotalCost
        totalAccrued = totalAccrued.add(o.totalCost());
    }

    /** O(1) — total cost of all deliveries ever recorded */
    public BigDecimal getTotalCost() {
        return totalAccrued;
    }

    /** O(n) — settle prorated cost up to time 'upTo' across all orders */
    public void payUpToTime(Instant upTo) {

//        payUpToTime(t) call pe har order ke liye:
//
//        Agar start >= t → skip (abhi tak kuch pay karne ko nahi).
//
//                right = min(end, t).
//
//                Agar right > settledUntil:
//
//        Unpaid window = [settledUntil, right).
//
//        deltaCost = rate × hours(right - settledUntil) // BigDecimal, 2 decimals
//
//        totalPaid += deltaCost
//
//        settledUntil = right
//
//        Warna (right ≤ settledUntil) → kuch pay nahi (already settled).
//
        if (upTo == null) throw new IllegalArgumentException("upTo cannot be null");
        BigDecimal delta = BigDecimal.ZERO;

        for (Order o : orders) {
            // If order starts after upTo, nothing to settle for it
            if (!o.start.isBefore(upTo)) continue;

            BigDecimal add = o.unsettledCostUpTo(upTo);
            if (add.signum() > 0) {
                o.settleUntil(upTo);
                delta = delta.add(add);
            }
        }

        // Update totalPaid once (idempotent across repeated calls for same upTo)
        totalPaid = totalPaid.add(delta);
    }

    /** O(1) — remaining liability; never negative (clamped) */
    public BigDecimal getCostToBePaid() {
        BigDecimal remaining = totalAccrued.subtract(totalPaid);
        return remaining.signum() < 0 ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP) : remaining;
    }

    // --- Demo / quick test ---
    public static void main(String[] args) {
        DeliverySystemPhase2 sys = new DeliverySystemPhase2();

        sys.addDriver(1, new BigDecimal("20.00"));
        sys.addDriver(2, new BigDecimal("30.50"));

        Instant t0 = Instant.parse("2025-10-31T00:00:00Z");

        // Orders: [0–3h @20], [1–4h @30.5]
        sys.addOrder(1, t0, t0.plus(Duration.ofHours(3)));                 // 60.00
        sys.addOrder(2, t0.plus(Duration.ofHours(1)), t0.plus(Duration.ofHours(4))); // 91.50

        System.out.println("TotalAccrued: " + sys.getTotalCost()); // 151.50

        // Pay up to 2h ⇒ settles 2h of first (40.00) + 1h of second (30.50) = 70.50
        sys.payUpToTime(t0.plus(Duration.ofHours(2)));
        System.out.println("CostToBePaid after t0+2h: " + sys.getCostToBePaid()); // 151.50 - 70.50 = 81.00

        // Idempotency: paying up to same time again should not change totals
        sys.payUpToTime(t0.plus(Duration.ofHours(2)));
        System.out.println("CostToBePaid after repeat settle: " + sys.getCostToBePaid()); // stays 81.00

        // Final settlement up to 5h (beyond both ends) ⇒ fully paid = 0
        sys.payUpToTime(t0.plus(Duration.ofHours(5)));
        System.out.println("CostToBePaid after full settle: " + sys.getCostToBePaid()); // 0.00
    }
}
