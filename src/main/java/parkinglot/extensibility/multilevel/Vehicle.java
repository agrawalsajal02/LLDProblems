package parkinglot.extensibility.multilevel;

public final class Vehicle {
    private final String vehicleId;
    private final VehicleType vehicleType;

    public Vehicle(String vehicleId, VehicleType vehicleType) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
