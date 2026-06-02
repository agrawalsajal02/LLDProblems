package companiesProblem.rippling.deliverySystem.refactoredFinal.phase2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DeliverySystem {
    Map<Integer, Driver> driverMap;
    Map<Integer, List<Delivery>> driverDeliveries;
    Map<Integer, NavigableMap<LocalDateTime, List<Delivery>>> unsettledDeliveriesByDriver;

    public DeliverySystem() {
        this.driverMap = new HashMap<>();
        this.driverDeliveries = new HashMap<>();
        this.unsettledDeliveriesByDriver = new HashMap<>();
    }

    public void addDriver(int driverId, double hourlyRate) {
        driverMap.put(driverId, new Driver(driverId, hourlyRate));
        driverDeliveries.put(driverId, new ArrayList<>());
        unsettledDeliveriesByDriver.put(driverId, new TreeMap<>());
    }

    public void addDelivery(int deliveryId, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        if (!driverMap.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver does not exist");
        }
        Delivery delivery = new Delivery(deliveryId, driverId, startTime, endTime);
        driverDeliveries.get(driverId).add(delivery);
        unsettledDeliveriesByDriver.get(driverId)
                .computeIfAbsent(delivery.settledUpToTime, time -> new ArrayList<>())
                .add(delivery);
    }

    // Phase 2 Core Requirement: Settle driver pay up to a specific time point
    public double settlePayment(int driverId, LocalDateTime upToTime) {
        Driver driver = driverMap.get(driverId);
        if (driver == null)
            return 0;

        double totalToPay = 0;
        NavigableMap<LocalDateTime, List<Delivery>> unsettledDeliveries = unsettledDeliveriesByDriver.get(driverId);
        NavigableMap<LocalDateTime, List<Delivery>> readyToSettle = unsettledDeliveries.headMap(upToTime, false);

        List<Delivery> candidates = new ArrayList<>();
        for (List<Delivery> deliveries : readyToSettle.values()) {
            candidates.addAll(deliveries);
        }
        readyToSettle.clear();

        List<Delivery> stillUnsettled = new ArrayList<>();
        for (Delivery delivery : candidates) {
            totalToPay += delivery.getUnsettledCost(upToTime, driver.hourlyRate);
            delivery.markSettled(upToTime);
            if (delivery.hasUnsettledTime()) {
                stillUnsettled.add(delivery);
            }
        }

        if (!stillUnsettled.isEmpty()) {
            unsettledDeliveries.computeIfAbsent(upToTime, time -> new ArrayList<>()).addAll(stillUnsettled);
        }

        return totalToPay;
    }

    public static void main(String[] args) {
        DeliverySystem system = new DeliverySystem();
        system.addDriver(1, 36.0); // $36/hr (makes math easy: $1 every 100 sec, $0.01/sec)

        LocalDateTime startTime = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime endTime = startTime.plusHours(1);
        system.addDelivery(101, 1, startTime, endTime);

        double firstPayout = system.settlePayment(1, startTime.plusMinutes(30));
        System.out.println("First Payout (after 30 minutes): $" + firstPayout); // Expected: 18.0

        double secondPayout = system.settlePayment(1, startTime.plusHours(2));
        System.out.println("Second Payout (after 2 hours): $" + secondPayout); // Expected: 18.0 (remaining)
    }
}
