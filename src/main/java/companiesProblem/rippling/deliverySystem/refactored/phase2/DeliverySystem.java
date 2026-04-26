package companiesProblem.rippling.deliverySystem.refactored.phase2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliverySystem {
    Map<Integer, Driver> driverMap;
    // Map driverId to their list of deliveries to make sweeping through deliveries
    // easier
    Map<Integer, List<Delivery>> driverDeliveries;

    public DeliverySystem() {
        this.driverMap = new HashMap<>();
        this.driverDeliveries = new HashMap<>();
    }

    public void addDriver(int driverId, double hourlyRate) {
        driverMap.put(driverId, new Driver(driverId, hourlyRate));
        driverDeliveries.put(driverId, new ArrayList<>());
    }

    public void addDelivery(int deliveryId, int driverId, long startTime, long endTime) {
        if (!driverMap.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver does not exist");
        }
        Delivery delivery = new Delivery(deliveryId, driverId, startTime, endTime);
        driverDeliveries.get(driverId).add(delivery);
    }

    // Phase 2 Core Requirement: Settle driver pay up to a specific time point
    public double settlePayment(int driverId, long upToTime) {
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

    public static void main(String[] args) {
        DeliverySystem system = new DeliverySystem();
        system.addDriver(1, 36.0); // $36/hr (makes math easy: $1 every 100 sec, $0.01/sec)

        // Delivery from t=0 to t=3600 (1 hour). Total value = $36.0
        system.addDelivery(101, 1, 0, 3600);

        // Payout at t=1800 (Half hour)
        double firstPayout = system.settlePayment(1, 1800);
        System.out.println("First Payout (at 1800s): $" + firstPayout); // Expected: 18.0

        // Payout at t=7200 (2 hours)
        double secondPayout = system.settlePayment(1, 7200);
        System.out.println("Second Payout (at 7200s): $" + secondPayout); // Expected: 18.0 (remaining)
    }
}
