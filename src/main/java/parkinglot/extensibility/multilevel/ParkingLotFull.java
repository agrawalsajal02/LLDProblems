package parkinglot.extensibility.multilevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ParkingLotFull {
    private final List<ParkingLevel> levels;
    private final Map<String, ParkingTicket> activeTicketsByTicketId;
    private final Map<String, ParkingTicket> activeTicketsByVehicleId;

    public ParkingLotFull(List<ParkingLevel> levels) {
        this.levels = new ArrayList<>(levels);
        this.activeTicketsByTicketId = new HashMap<>();
        this.activeTicketsByVehicleId = new HashMap<>();
    }

    public ParkingTicket park(Vehicle vehicle) {
        if (activeTicketsByVehicleId.containsKey(vehicle.getVehicleId())) {
            throw new RuntimeException("Vehicle already parked: " + vehicle.getVehicleId());
        }

        ParkingSpot spot = findAvailableSpot(vehicle);
        if (spot == null) {
            throw new RuntimeException("No compatible spot available for vehicle type " + vehicle.getVehicleType());
        }

        spot.park(vehicle);
        ParkingTicket ticket = new ParkingTicket(
            UUID.randomUUID().toString(),
            vehicle.getVehicleId(),
            vehicle.getVehicleType(),
            spot.getLocation(),
            System.currentTimeMillis()
        );
        activeTicketsByTicketId.put(ticket.getTicketId(), ticket);
        activeTicketsByVehicleId.put(vehicle.getVehicleId(), ticket);
        return ticket;
    }

    public ParkingTicket unpark(String ticketId) {
        ParkingTicket ticket = activeTicketsByTicketId.remove(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid or already used ticket: " + ticketId);
        }

        activeTicketsByVehicleId.remove(ticket.getVehicleId());
        ParkingSpot spot = findSpot(ticket.getLocation());
        if (spot == null) {
            throw new RuntimeException("Spot not found for ticket: " + ticketId);
        }
        spot.unpark();
        return ticket;
    }

    public ParkingTicket searchByTicketId(String ticketId) {
        return activeTicketsByTicketId.get(ticketId);
    }

    public ParkingTicket searchByVehicleId(String vehicleId) {
        return activeTicketsByVehicleId.get(vehicleId);
    }

    private ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            for (ParkingRow row : level.getRows()) {
                for (ParkingSpot spot : row.getSpots()) {
                    if (spot.isFree() && spot.canFit(vehicle)) {
                        return spot;
                    }
                }
            }
        }
        return null;
    }

    private ParkingSpot findSpot(ParkingLocation location) {
        for (ParkingLevel level : levels) {
            if (!level.getLevelId().equals(location.getLevelId())) {
                continue;
            }
            for (ParkingRow row : level.getRows()) {
                if (!row.getRowId().equals(location.getRowId())) {
                    continue;
                }
                for (ParkingSpot spot : row.getSpots()) {
                    if (spot.getSpotId().equals(location.getSpotId())) {
                        return spot;
                    }
                }
            }
        }
        return null;
    }
}
