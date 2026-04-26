package carrentalsystem;

import java.util.ArrayList;
import java.util.List;

public final class Store {
    private final String id;
    private final Location location;
    private final List<Vehicle> vehicles = new ArrayList<>();

    public Store(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findAvailableVehicle(VehicleType type) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType() == type && vehicle.getStatus() == VehicleStatus.AVAILABLE) {
                return vehicle;
            }
        }
        return null;
    }
}
