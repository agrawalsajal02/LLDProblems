package companiesProblem.rippling.deliverySystem.Phase4;//package rippling.deliverySystem.Phase4;
//
//import rippling.deliverySystem.Delivery;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.*;
//
///**
// * Phase 4 — Analytics & Overlaps (Sweep-line)
// * -------------------------------------------
// * Adds:
// *   - int getMaxActiveDriversInLast24Hours(Instant now)
// *     Count DISTINCT drivers active in [now-24h, now) using a sweep-line.
// *
// * Stretch:
// *   - Map<Integer, Integer> getConcurrentDeliveriesPerDriver(Instant from, Instant to)
// *     For each driver, the maximum number of concurrent deliveries within [from, to).
// *
// * Complexity: O(m log m), where m is the number of interval boundaries in the window.
// *
// * Notes:
// *   - Keeps the simple Phase 1 storage so you can add drivers/orders and run analytics.
// *   - Currency/time scaffolding retained for completeness (not required for this phase).
// */
//public class DeliverySystemPhase4 {
//
//    // ===== Domain =====
//    static final class Driver {
//        final int id;
//        final BigDecimal hourlyRate;
//
//        Driver(int id, BigDecimal rate) {
//            if (rate == null || rate.signum() < 0)
//                throw new IllegalArgumentException("Hourly rate must be non-negative");
//            this.id = id;
//            this.hourlyRate = rate.setScale(2, RoundingMode.HALF_UP);
//        }
//    }
//
//    static final class Order {
//        final int driverId;
//        final Instant start;
//        final Instant end;
//        final BigDecimal ratePerHourSnapshot;
//
//        Order(int driverId, Instant start, Instant end, BigDecimal rate) {
//            if (start == null || end == null || !end.isAfter(start))
//                throw new IllegalArgumentException("Invalid start/end");
//            this.driverId = driverId;
//            this.start = start;
//            this.end = end;
//            this.ratePerHourSnapshot = rate;
//        }
//    }
//
//    // ===== State =====
//    private final Map<Integer, Driver> drivers = new HashMap<>();
//    private final List<Order> orders = new ArrayList<>();
//    private BigDecimal totalAccrued = BigDecimal.ZERO; // not needed for analytics but kept for completeness
//
//    // ===== Phase 1 APIs (to set up data quickly) =====
//    public void addDriver(int driverId, BigDecimal hourlyRate) {
//        if (drivers.containsKey(driverId))
//            throw new IllegalArgumentException("Driver already exists: " + driverId);
//        drivers.put(driverId, new Driver(driverId, hourlyRate));
//    }
//
//    public void addOrder(int driverId, Instant start, Instant end) {
//        Driver d = drivers.get(driverId);
//        if (d == null) throw new NoSuchElementException("Driver not found: " + driverId);
//        orders.add(new Order(driverId, start, end, d.hourlyRate));
//        // (Optional) keep accrued for earlier phases
//        long seconds = Duration.between(start, end).getSeconds();
//        BigDecimal hours = new BigDecimal(seconds).divide(new BigDecimal(3600), 9, RoundingMode.HALF_UP);
//        totalAccrued = totalAccrued.add(hours.multiply(d.hourlyRate).setScale(2, RoundingMode.HALF_UP));
//    }
//
//    public BigDecimal getTotalCost() { return totalAccrued; }
//
//    // ===== Phase 4 — Analytics =====
//
//    /**
//     * Max number of DISTINCT drivers that were active at the same time within [now-24h, now).
//     * Uses sweep-line over interval boundaries intersected with the 24h window.
//     */
//    public int getMaxActiveDriversInLast24Hours(Instant now) {
//        if (now == null) throw new IllegalArgumentException("now cannot be null");
//        Instant windowStart = now.minus(Duration.ofHours(24));
//        return getMaxActiveDrivers(windowStart, now);
//    }
//
//
//    int getMaxActiveDriversInLast24Hours(long currentTimeMillis) {
//        long windowStart = currentTimeMillis - 24 * 3600 * 1000;
//        Set<Integer> active = new HashSet<>();
//
//        for (Order d : orders)
//            if (d.endTime >= windowStart && d.startTime <= currentTimeMillis)
//                active.add(d.driver.id);
//
//        return active.size();
//    }
//
//
//
//    /**
//     * Helper: generic distinct-driver sweep over [from, to).
//     * Builds event boundaries (start +1, end -1) for intersecting slices,
//     * then sweeps while tracking per-driver active counts and the distinct total.
//     */
//    private int getMaxActiveDrivers(Instant from, Instant to) {
//        if (!to.isAfter(from)) return 0;
//
//        List<Boundary> events = new ArrayList<>();
//        for (Order o : orders) {
//            Instant s = max(o.start, from);
//            Instant e = min(o.end, to);
//            if (e.isAfter(s)) {
//                // start event (+1), end event (-1)
//                events.add(new Boundary(s, o.driverId, +1));
//                events.add(new Boundary(e, o.driverId, -1));
//            }
//        }
//
//        // Sort by time; tie-breaker: process END (-1) before START (+1)
//        // to avoid double-counting on boundary touch points.
//        events.sort(Comparator
//                .comparing((Boundary b) -> b.time)
//                .thenComparingInt(b -> b.delta)); // -1 before +1
//
//        int distinct = 0, best = 0;
//        Map<Integer, Integer> activeByDriver = new HashMap<>();
//
//        for (Boundary b : events) {
//            int cur = activeByDriver.getOrDefault(b.driverId, 0);
//            int next = cur + b.delta;
//
//            if (b.delta > 0) {
//                // start
//                if (next == 1) distinct++;  // driver transitioned inactive -> active
//                activeByDriver.put(b.driverId, next);
//            } else {
//                // end
//                if (next == 0) {
//                    activeByDriver.remove(b.driverId);
//                    distinct--;              // active -> inactive
//                } else {
//                    activeByDriver.put(b.driverId, next);
//                }
//            }
//            if (distinct > best) best = distinct;
//        }
//        return best;
//    }
//
//    /**
//     * Stretch:
//     * For each driver, return the MAX number of concurrent deliveries they had within [from, to).
//     * Sweep once globally and track per-driver counts + per-driver maximums.
//     */
//    public Map<Integer, Integer> getConcurrentDeliveriesPerDriver(Instant from, Instant to) {
//        if (from == null || to == null || !to.isAfter(from))
//            throw new IllegalArgumentException("Invalid range");
//        List<Boundary> events = new ArrayList<>();
//        for (Order o : orders) {
//            Instant s = max(o.start, from);
//            Instant e = min(o.end, to);
//            if (e.isAfter(s)) {
//                events.add(new Boundary(s, o.driverId, +1));
//                events.add(new Boundary(e, o.driverId, -1));
//            }
//        }
//        events.sort(Comparator
//                .comparing((Boundary b) -> b.time)
//                .thenComparingInt(b -> b.delta)); // -1 before +1
//
//        Map<Integer, Integer> cur = new HashMap<>();
//        Map<Integer, Integer> best = new HashMap<>();
//
//        for (Boundary b : events) {
//            int c = cur.getOrDefault(b.driverId, 0) + b.delta;
//            if (b.delta > 0) {
//                cur.put(b.driverId, c);
//                best.put(b.driverId, Math.max(best.getOrDefault(b.driverId, 0), c));
//            } else {
//                if (c == 0) cur.remove(b.driverId);
//                else cur.put(b.driverId, c);
//            }
//        }
//        // Ensure all drivers appear (even those with 0 in the window)
//        for (Integer id : drivers.keySet()) best.putIfAbsent(id, 0);
//        return best;
//    }
//
//    // ===== Helpers =====
//    private static Instant min(Instant a, Instant b) { return a.isBefore(b) ? a : b; }
//    private static Instant max(Instant a, Instant b) { return a.isAfter(b) ? a : b; }
//
//    private static final class Boundary {
//        final Instant time;
//        final int driverId;
//        final int delta; // +1 start, -1 end
//        Boundary(Instant time, int driverId, int delta) {
//            this.time = time; this.driverId = driverId; this.delta = delta;
//        }
//    }
//
//    // ===== Demo / quick test =====
//    public static void main(String[] args) {
//        DeliverySystemPhase4 sys = new DeliverySystemPhase4();
//
//        // Drivers
//        sys.addDriver(1, new BigDecimal("20.00"));
//        sys.addDriver(2, new BigDecimal("25.00"));
//        sys.addDriver(3, new BigDecimal("30.00"));
//
//        Instant t0 = Instant.parse("2025-10-31T00:00:00Z");
//
//        // Craft staggered overlaps inside a 24h window ending at t0+10h
//        // Driver 1: [0h, 4h), [5h, 8h)
//        sys.addOrder(1, t0, t0.plus(Duration.ofHours(4)));
//        sys.addOrder(1, t0.plus(Duration.ofHours(5)), t0.plus(Duration.ofHours(8)));
//
//        // Driver 2: [2h, 6h)
//        sys.addOrder(2, t0.plus(Duration.ofHours(2)), t0.plus(Duration.ofHours(6)));
//
//        // Driver 3: [3h, 5h), [7h, 9h)
//        sys.addOrder(3, t0.plus(Duration.ofHours(3)), t0.plus(Duration.ofHours(5)));
//        sys.addOrder(3, t0.plus(Duration.ofHours(7)), t0.plus(Duration.ofHours(9)));
//
//        Instant now = t0.plus(Duration.ofHours(10));
//
//        int maxDistinct = sys.getMaxActiveDriversInLast24Hours(now);
//        System.out.println("Max distinct active drivers in last 24h: " + maxDistinct);
//        // Expected around 3 between [3h,4h): D1, D2, D3 all active.
//
//        Map<Integer, Integer> perDriverMax = sys.getConcurrentDeliveriesPerDriver(
//                t0, now);
//        System.out.println("Per-driver max concurrent deliveries in [t0, now): " + perDriverMax);
//        // With given data each driver has at most 1 concurrent delivery, so all 1s.
//    }
//}
//
