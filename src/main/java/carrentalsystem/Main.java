package carrentalsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public final class Main {
    TreeMap<Integer, List<String>> map = new TreeMap<>(Collections.reverseOrder());

    public static void main(String[] args) {





        Store store = new Store("S1", new Location("Bangalore"));
        store.addVehicle(new Vehicle("V1", VehicleType.CAR, 2000));
        store.addVehicle(new Vehicle("V2", VehicleType.BIKE, 700));

        VehicleRentalSystem rentalSystem = new VehicleRentalSystem(List.of(store));
        Reservation reservation = rentalSystem.reserve("R1", new User("U1", "Sajal"), new Location("Bangalore"), VehicleType.CAR, 3);

        System.out.println("Reservation status: " + reservation.getStatus());
        System.out.println("Bill amount: " + reservation.getBillAmount());
    }

    public void addScore(String user, int score) {
        map.computeIfAbsent(score, k -> new ArrayList<>()).add(user);
    }
}
