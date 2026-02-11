package parkinglot;

import java.util.ArrayList;
import java.util.List;

public final class ParkingLotMain {
    public static void main(String[] args) {
        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot("M1", SpotType.MOTORCYCLE));
        spots.add(new ParkingSpot("C1", SpotType.CAR));
        spots.add(new ParkingSpot("L1", SpotType.LARGE));

        ParkingLot lot = new ParkingLot(spots, 500);
        Ticket ticket = lot.enter(VehicleType.CAR);
        long fee = lot.exit(ticket.getId());
        System.out.println("Fee cents: " + fee);
    }
}
