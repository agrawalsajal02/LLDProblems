package companiesProblem.rippling.deliverySystem;//package rippling.deliverySystem;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * Basic & Extensible Delivery Payment System
// * -----------------------------------------
// * - Money: BigDecimal (currency-safe)
// * - Time: Instant (UTC, timezone-agnostic)
// * - Rate: BigDecimal per hour
// * - Cost accrual: computed at insertion for getTotalCost() O(1)
// * - Settlement: payUpToTime() settles prorated amounts up to the given Instant
// * - Analytics: getMaxActiveDriversInLast24Hours(now)
// * - Concurrency: RW lock around state mutations
// *
// * API:
// *   addDriver(int driverId, BigDecimal hourlyRate)
// *   addOrder(int driverId, Instant start, Instant end)
// *   payUpToTime(Instant upTo)
// *   BigDecimal getCostToBePaid()
// *   BigDecimal getTotalCost()
// *   int getMaxActiveDriversInLast24Hours(Instant now)
// *   void updateHourlyRate(int driverId, BigDecimal newRate)
// */
//public static class DeliveryPaymentSystem {
//
//    // ====== Domain ======
//    public static final class Driver {
//        final int id;
//        volatile BigDecimal hourlyRate; // current rate (affects future orders)
//        Driver(int id, BigDecimal rate) {
//            this.id = id;
//            this.hourlyRate = requireNonNegative(rate, "hourlyRate");
//        }
//    }
//
//    /**
//     * A delivery order. We snapshot the driver's hourly rate at order creation
//     * to avoid historical recompute when rates change later.
//     */
//    public static final class Order {
//        final int driverId;
//        final Instant start;
//        final Instant end; // invariant: end > start
//        final BigDecimal ratePerHourSnapshot;
//
//        // Settlement tracking (how much of this order's interval is already paid)
//        Instant settledUntil; // in [start, end]; monotonic non-decreasing
//
//        Order(int driverId, Instant start, Instant end, BigDecimal ratePerHour) {
//            if (start == null || end == null) throw new IllegalArgumentException("start/end cannot be null");
//            if (!end.isAfter(start)) throw new IllegalArgumentException("end must be after start");
//            this.driverId = driverId;
//            this.start = start;
//            this.end = end;
//            this.ratePerHourSnapshot = requireNonNegative(ratePerHour, "rate");
//            this.settledUntil = start; // nothing paid yet
//        }
//
//        BigDecimal totalCost() {
//            return costBetween(start, end);
//        }
//
//        BigDecimal unsettledCostUpTo(Instant upTo) {
//            Instant right = min(end, upTo);
//            if (!right.isAfter(settledUntil)) return BigDecimal.ZERO; // nothing new to pay
//            return costBetween(settledUntil, right);
//        }
//
//        BigDecimal costBetween(Instant a, Instant b) {
//            if (!b.isAfter(a)) return BigDecimal.ZERO;
//            long seconds = Duration.between(a, b).getSeconds();
//            // hours = seconds / 3600 with fractional component
//            BigDecimal hours = new BigDecimal(seconds).divide(new BigDecimal(3600), 9, RoundingMode.DOWN);
//            return hours.multiply(ratePerHourSnapshot).setScale(2, RoundingMode.HALF_UP);
//        }
//
//        void settleUntil(Instant upTo) {
//            Instant right = min(end, upTo);
//            if (right.isAfter(settledUntil)) {
//                settledUntil = right;
//            }
//        }
//    }
//
//    // ====== State ======
//    private final Map<Integer, Driver> drivers = new ConcurrentHashMap<>();
//    private final List<Order> orders = new ArrayList<>();
//
//    // Precomputed totals for O(1) getTotalCost and O(1) getCostToBePaid
//    private BigDecimal totalAccrued = BigDecimal.ZERO; // sum of all orders' total cost
//    private BigDecimal totalPaid = BigDecimal.ZERO;    // sum paid via settlements
//
//    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
//
//
//    // === Extra APIs to match interview variants ===
//    /** Returns the current hourly rate for a driver. */
//    public BigDecimal getDriverHourlyRate(int driverId) {
//        rw.readLock().lock();
//        try {
//            Driver d = drivers.get(driverId);
//            if (d == null) throw new NoSuchElementException("unknown driver: " + driverId);
//            return d.hourlyRate;
//        } finally { rw.readLock().unlock(); }
//    }
//
//    /**
//     * Records a completed delivery by duration (hours/mins), ending at 'now'.
//     * This mirrors interview API: record_delivery(driverId, duration_in_hours).
//     */
//    public void recordDelivery(int driverId, Duration duration) {
//        if (duration.isNegative() || duration.isZero()) throw new IllegalArgumentException("duration must be positive");
//        Instant end = Instant.now();
//        Instant start = end.minus(duration);
//        addOrder(driverId, start, end);
//    }
//
//    /** Alias for interview wording */
//    public BigDecimal calculateTotalCost() { return getTotalCost(); }
//        finally { rw.readLock().unlock(); }
//}
//
///** Update a driver rate — affects only FUTURE orders. */
//public void updateHourlyRate(int driverId, BigDecimal newRate) {
//    rw.writeLock().lock();
//    try {
//        Driver d = drivers.get(driverId);
//        if (d == null) throw new NoSuchElementException("unknown driver: " + driverId);
//        d.hourlyRate = requireNonNegative(newRate, "newRate");
//    } finally { rw.writeLock().unlock(); }
//}
//
///**
// * Analytics: maximum number of DISTINCT drivers concurrently active in the last 24h.
// * Uses sweep-line over order boundaries with a multiset-per-driver.
// */
//public int getMaxActiveDriversInLast24Hours(Instant now) {
//    Instant windowStart = now.minus(Duration.ofHours(24));
//    List<Boundary> events = new ArrayList<>();
//    rw.readLock().lock();
//    try {
//        for (Order o : orders) {
//            // intersect [o.start, o.end) with [windowStart, now)
//            Instant s = max(o.start, windowStart);
//            Instant e = min(o.end, now);
//            if (e.isAfter(s)) {
//                events.add(new Boundary(s, o.driverId, +1));
//                events.add(new Boundary(e, o.driverId, -1));
//            }
//        }
//    } finally { rw.readLock().unlock(); }
//    events.sort(Comparator.comparing((Boundary b) -> b.time).thenComparing(b -> b.delta));
//
//    int maxDistinct = 0;
//    Map<Integer, Integer> activeCountByDriver = new HashMap<>();
//    int distinct = 0;
//    for (Boundary b : events) {
//        int c = activeCountByDriver.getOrDefault(b.driverId, 0) + b.delta;
//        if (b.delta > 0 && c == 1) distinct++;      // driver became active
//        else if (b.delta < 0 && c == 0) distinct--; // driver became inactive
//        if (c == 0) activeCountByDriver.remove(b.driverId);
//        else activeCountByDriver.put(b.driverId, c);
//        maxDistinct = Math.max(maxDistinct, distinct);
//    }
//    return maxDistinct;
//}
//
//
//private final PriorityQueue<Delivery> pq =
//        new PriorityQueue<>(Comparator.comparingLong(d -> d.endTime));
//
//private final Map<Integer,Integer> active = new HashMap<>();
//
//void addDelivery(Delivery d) {
//    pq.add(d);
//    active.merge(d.driver.id, 1, Integer::sum);
//}
//
//int getMaxActiveDriversInLast24Hours(long now) {
//    long cutoff = now - 24L * 3600 * 1000;
//
//    while (!pq.isEmpty() && pq.peek().endTime < cutoff) {
//        Delivery old = pq.poll();
//        active.computeIfPresent(old.driver.id, (id, c) -> {
//            int newVal = c - 1;
//            return newVal <= 0 ? null : newVal;
//        });
//    }
//    return active.size();
//}
//
//// ====== Helpers ======
//
//private static final class Boundary {
//    final Instant time; final int driverId; final int delta; // +1 start, -1 end
//    Boundary(Instant time, int driverId, int delta) { this.time = time; this.driverId = driverId; this.delta = delta; }
//}
//
//private static Instant min(Instant a, Instant b) { return a.isBefore(b) ? a : b; }
//private static Instant max(Instant a, Instant b) { return a.isAfter(b) ? a : b; }
//
//private static BigDecimal requireNonNegative(BigDecimal v, String name) {
//    if (v == null) throw new IllegalArgumentException(name + " cannot be null");
//    if (v.signum() < 0) throw new IllegalArgumentException(name + " cannot be negative");
//    return v;
//}
//
//// ====== Demo ======
//public static void main(String[] args) {
//    DeliveryPaymentSystem sys = new DeliveryPaymentSystem();
//    Instant t0 = Instant.parse("2025-10-31T00:00:00Z");
//    sys.addDriver(1, new BigDecimal("20.00"));
//    sys.addDriver(2, new BigDecimal("30.50"));
//
//    sys.addOrder(1, t0, t0.plus(Duration.ofHours(3))); // 3h * 20 = 60.00
//    sys.addOrder(2, t0.plus(Duration.ofHours(1)), t0.plus(Duration.ofHours(4))); // 3h * 30.5 = 91.50
//
//    System.out.println("Total cost: " + sys.getTotalCost()); // 151.50
//    sys.payUpToTime(t0.plus(Duration.ofHours(2))); // settle first 2h of driver1 and first 1h of driver2
//    System.out.println("Remaining: " + sys.getCostToBePaid());
//    System.out.println("Max active drivers last 24h: " + sys.getMaxActiveDriversInLast24Hours(t0.plus(Duration.ofHours(5))));
//}
//}
