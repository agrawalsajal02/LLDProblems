package parkinglot.extensibility.multilevel;

public final class ParkingSpot {
    private final String spotId;
    private final SpotType spotType;
    private final ParkingLocation location;
    private Vehicle parkedVehicle;

    public ParkingSpot(String levelId, String rowId, String spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.location = new ParkingLocation(levelId, rowId, spotId);
    }

    public String getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public ParkingLocation getLocation() {
        return location;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    public boolean canFit(Vehicle vehicle) {
        if (vehicle.getVehicleType() == VehicleType.MOTORCYCLE) {
            return true;
        }
        return vehicle.getVehicleType() == VehicleType.CAR && spotType == SpotType.CAR;
    }

    public void park(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void unpark() {
        this.parkedVehicle = null;
    }
}
