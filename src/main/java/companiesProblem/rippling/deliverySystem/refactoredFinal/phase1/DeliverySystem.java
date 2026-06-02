package companiesProblem.rippling.deliverySystem.refactoredFinal.phase1;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DeliverySystem {
    Map<Integer, Driver> driverMap;
    Map<Integer, Delivery> deliveryMap;

    public DeliverySystem() {
        this.driverMap = new HashMap<>();
        this.deliveryMap = new HashMap<>();
    }

    public void addDriver(int driverId, double hourlyRate) {
        driverMap.put(driverId, new Driver(driverId, hourlyRate));
    }

    public void addDelivery(int deliveryId, int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        if (!driverMap.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver does not exist");
        }
        deliveryMap.put(deliveryId, new Delivery(deliveryId, driverId, startTime, endTime));
    }

    // Phase 1 Core Requirement: Calculate base cost for a delivery
    public double calculateDeliveryCost(int deliveryId) {
        Delivery delivery = deliveryMap.get(deliveryId);
        if (delivery == null)
            return 0;

        Driver driver = driverMap.get(delivery.driverId);
        return delivery.getDurationInHours() * driver.hourlyRate;
    }

    public static void main(String[] args) {
        DeliverySystem system = new DeliverySystem();
        system.addDriver(1, 20.0); // $20/hr

        LocalDateTime startTime = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime endTime = startTime.plusHours(2);
        system.addDelivery(101, 1, startTime, endTime);

        System.out.println("Cost for Delivery 101: $" + system.calculateDeliveryCost(101));
        // Expected: 40.0
    }
}
