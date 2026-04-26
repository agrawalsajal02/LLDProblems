package carrentalsystem;

public final class Vehicle {
    private final String id;
    private final VehicleType type;
    private final int pricePerDay;
    private VehicleStatus status;

    public Vehicle(String id, VehicleType type, int pricePerDay) {
        this.id = id;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.status = VehicleStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void reserve() {
        status = VehicleStatus.RESERVED;
    }
}
