package parkinglot.extensibility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import parkinglot.ParkingSpot;
import parkinglot.SpotType;
import parkinglot.Ticket;
import parkinglot.VehicleType;

public class PerTypePricingParkingLot {
    private final List<ParkingSpot> spots;
    private final Map<String, Ticket> activeTickets;
    private final Set<String> occupiedSpotIds;
    private final Map<VehicleType, Long> hourlyRates;

    public PerTypePricingParkingLot(List<ParkingSpot> spots, Map<VehicleType, Long> hourlyRates) {
        this.spots = spots;
        this.activeTickets = new HashMap<>();
        this.occupiedSpotIds = new HashSet<>();
        this.hourlyRates = new HashMap<>(hourlyRates);
    }

    public Ticket enter(VehicleType vehicleType) {
        ParkingSpot spot = findAvailableSpot(vehicleType);
        if (spot == null) {
            throw new RuntimeException("No available spots for vehicle type " + vehicleType);
        }

        occupiedSpotIds.add(spot.getId());

        String ticketId = UUID.randomUUID().toString();
        long entryTime = System.currentTimeMillis();
        Ticket ticket = new Ticket(ticketId, spot.getId(), vehicleType, entryTime);
        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    public long exit(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            throw new RuntimeException("Invalid ticket ID");
        }

        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Ticket not found or already used");
        }

        long exitTime = System.currentTimeMillis();
        long fee = computeFee(ticket.getVehicleType(), ticket.getEntryTimeMs(), exitTime);

        occupiedSpotIds.remove(ticket.getSpotId());
        activeTickets.remove(ticketId);

        return fee;
    }

    private ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        SpotType requiredSpotType = mapVehicleTypeToSpotType(vehicleType);
        for (ParkingSpot spot : spots) {
            if (!occupiedSpotIds.contains(spot.getId()) && spot.getSpotType() == requiredSpotType) {
                return spot;
            }
        }
        return null;
    }

    private SpotType mapVehicleTypeToSpotType(VehicleType vehicleType) {
        if (vehicleType == VehicleType.MOTORCYCLE)
            return SpotType.MOTORCYCLE;
        if (vehicleType == VehicleType.CAR)
            return SpotType.CAR;
        if (vehicleType == VehicleType.LARGE)
            return SpotType.LARGE;
        throw new RuntimeException("Unknown vehicle type");
    }

    private long computeFee(VehicleType type, long entryTimeMs, long exitTimeMs) {
        long durationMs = exitTimeMs - entryTimeMs;
        long hourMs = 1000L * 60L * 60L;
        long hours = durationMs / hourMs;

        // if there’s any leftover minutes/seconds beyond full hours, charge one extra
        // hour.
        if (durationMs % hourMs > 0) {
            hours++;
        }
        long rate = hourlyRates.getOrDefault(type, 0L);
        return hours * rate;
    }
}
