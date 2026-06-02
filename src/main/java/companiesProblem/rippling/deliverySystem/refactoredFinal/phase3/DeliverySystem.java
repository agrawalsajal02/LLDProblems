package companiesProblem.rippling.deliverySystem.refactoredFinal.phase3;

import java.time.LocalDateTime;
import java.util.*;

public class DeliverySystem {
    Map<Integer, Driver> driverMap;
    Map<Integer, List<Delivery>> driverDeliveries;
    NavigableMap<LocalDateTime, List<Delivery>> deliveriesByEndTime;

    // Helper class for Phase 3 sweep line algorithm
    private static class Event {
        LocalDateTime time;
        int driverId;
        int type; // +1 for start, -1 for end

        Event(LocalDateTime time, int driverId, int type) {
            this.time = time;
            this.driverId = driverId;
            this.type = type;
        }
    }

    public DeliverySystem() {
        this.driverMap = new HashMap<>();
        this.driverDeliveries = new HashMap<>();
        this.deliveriesByEndTime = new TreeMap<>();
    }

    public void addDriver(int driverId, double hourlyRate) {
        driverMap.put(driverId, new Driver(driverId, hourlyRate));
        driverDeliveries.put(driverId, new ArrayList<>());
    }

    public void addDelivery(int deliveryId, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        if (!driverMap.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver does not exist");
        }
        Delivery delivery = new Delivery(deliveryId, driverId, startTime, endTime);
        driverDeliveries.get(driverId).add(delivery);
        deliveriesByEndTime.computeIfAbsent(endTime, time -> new ArrayList<>()).add(delivery);
    }

    public double settlePayment(int driverId, LocalDateTime upToTime) {
        Driver driver = driverMap.get(driverId);
        if (driver == null)
            return 0;

        double totalToPay = 0;
        List<Delivery> deliveries = driverDeliveries.get(driverId);

        for (Delivery delivery : deliveries) {
            totalToPay += delivery.getUnsettledCost(upToTime, driver.hourlyRate);
            delivery.markSettled(upToTime);
        }

        return totalToPay;
    }

    // Phase 3 Core Requirement: Analytics - Max active distinct drivers in time
    // window
    public int getMaxActiveDriversInTimeWindow(LocalDateTime searchStartTime, LocalDateTime searchEndTime) {
        List<Event> events = new ArrayList<>();

        for (List<Delivery> deliveries : deliveriesByEndTime.tailMap(searchStartTime, false).values()) {
            for (Delivery delivery : deliveries) {
                LocalDateTime startOverlap = max(delivery.startTime, searchStartTime);
                LocalDateTime endOverlap = min(delivery.endTime, searchEndTime);

                // If overlap exists
                if (startOverlap.isBefore(endOverlap)) {
                    events.add(new Event(startOverlap, delivery.driverId, 1));
                    events.add(new Event(endOverlap, delivery.driverId, -1));
                }
            }
        }

        // Sort events: primary by time, secondary by type (process ends before starts
        // if same time)
        events.sort((a, b) -> {
            int timeComparison = a.time.compareTo(b.time);
            if (timeComparison != 0)
                return timeComparison;
            return Integer.compare(a.type, b.type);
        });

        int maxDistinct = 0;
        int currentDistinct = 0;
        Map<Integer, Integer> driverActiveCount = new HashMap<>();

        for (Event event : events) {
            int currentDeliveriesForDriver = driverActiveCount.getOrDefault(event.driverId, 0);

            if (event.type == 1) { // Adding a delivery
                if (currentDeliveriesForDriver == 0) {
                    currentDistinct++; // New driver became active
                }
                driverActiveCount.put(event.driverId, currentDeliveriesForDriver + 1);
            } else { // Ending a delivery
                if (currentDeliveriesForDriver == 1) {
                    currentDistinct--; // Driver has no more active deliveries
                }
                driverActiveCount.put(event.driverId, currentDeliveriesForDriver - 1);
            }

            maxDistinct = Math.max(maxDistinct, currentDistinct);
        }

        return maxDistinct;
    }

    private LocalDateTime min(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second) ? first : second;
    }

    private LocalDateTime max(LocalDateTime first, LocalDateTime second) {
        return first.isAfter(second) ? first : second;
    }

    public static void main(String[] args) {
        DeliverySystem system = new DeliverySystem();
        system.addDriver(1, 20.0);
        system.addDriver(2, 30.0);

        LocalDateTime baseTime = LocalDateTime.of(2026, 1, 1, 10, 0);

        // Driver 1 active 100 to 200
        system.addDelivery(101, 1, baseTime.plusMinutes(100), baseTime.plusMinutes(200));
        // Driver 2 active 150 to 250
        system.addDelivery(102, 2, baseTime.plusMinutes(150), baseTime.plusMinutes(250));

        // At time 150-200, both are active.
        System.out.println("Max concurrent drivers: "
                + system.getMaxActiveDriversInTimeWindow(baseTime, baseTime.plusMinutes(300)));
        // Expected: 2

        // At time 0-120, only driver 1
        System.out.println("Max concurrent drivers in 0-120: "
                + system.getMaxActiveDriversInTimeWindow(baseTime, baseTime.plusMinutes(120)));
        // Expected: 1
    }
}
