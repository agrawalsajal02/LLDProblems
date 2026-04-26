package parkinglot.extensibility.multilevel;

import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        ParkingLevel level1 = new ParkingLevel(
            "L1",
            Arrays.asList(
                new ParkingRow(
                    "R1",
                    Arrays.asList(
                        new ParkingSpot("L1", "R1", "M1", SpotType.MOTORCYCLE),
                        new ParkingSpot("L1", "R1", "C1", SpotType.CAR)
                    )
                ),
                new ParkingRow(
                    "R2",
                    Arrays.asList(
                        new ParkingSpot("L1", "R2", "C2", SpotType.CAR)
                    )
                )
            )
        );

        ParkingLevel level2 = new ParkingLevel(
            "L2",
            Arrays.asList(
                new ParkingRow(
                    "R1",
                    Arrays.asList(
                        new ParkingSpot("L2", "R1", "M2", SpotType.MOTORCYCLE),
                        new ParkingSpot("L2", "R1", "C3", SpotType.CAR)
                    )
                )
            )
        );

        ParkingLotFull parkingLot = new ParkingLotFull(Arrays.asList(level1, level2));

        Vehicle bike = new Vehicle("BIKE-101", VehicleType.MOTORCYCLE);
        Vehicle car = new Vehicle("CAR-201", VehicleType.CAR);

        ParkingTicket bikeTicket = parkingLot.park(bike);
        ParkingTicket carTicket = parkingLot.park(car);

        System.out.println("Bike ticket: " + bikeTicket);
        System.out.println("Car ticket: " + carTicket);

        System.out.println("Search bike by vehicle: " + parkingLot.searchByVehicleId("BIKE-101"));
        System.out.println("Search car by ticket: " + parkingLot.searchByTicketId(carTicket.getTicketId()));

        parkingLot.unpark(bikeTicket.getTicketId());
        System.out.println("Bike after unpark: " + parkingLot.searchByVehicleId("BIKE-101"));
    }
}
