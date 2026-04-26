package parkinglot.extensibility.multilevel;

public final class ParkingTicket {
    private final String ticketId;
    private final String vehicleId;
    private final VehicleType vehicleType;
    private final ParkingLocation location;
    private final long entryTimeMs;

    public ParkingTicket(
        String ticketId,
        String vehicleId,
        VehicleType vehicleType,
        ParkingLocation location,
        long entryTimeMs
    ) {
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.location = location;
        this.entryTimeMs = entryTimeMs;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingLocation getLocation() {
        return location;
    }

    public long getEntryTimeMs() {
        return entryTimeMs;
    }

    @Override
    public String toString() {
        return "ParkingTicket{"
            + "ticketId='" + ticketId + '\''
            + ", vehicleId='" + vehicleId + '\''
            + ", vehicleType=" + vehicleType
            + ", location=" + location
            + ", entryTimeMs=" + entryTimeMs
            + '}';
    }
}
