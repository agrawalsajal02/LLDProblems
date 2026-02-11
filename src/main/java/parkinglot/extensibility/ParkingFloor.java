package parkinglot.extensibility;

import java.util.List;

import parkinglot.ParkingSpot;
import parkinglot.SpotType;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingSpot findAvailableSpot(SpotType spotType, java.util.Set<String> occupiedSpotIds) {
        for (ParkingSpot spot : spots) {
            if (spot.getSpotType() == spotType && !occupiedSpotIds.contains(spot.getId())) {
                return spot;
            }
        }
        return null;
    }
}
