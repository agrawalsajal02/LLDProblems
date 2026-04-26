package carrentalsystem;

import java.util.List;

public final class VehicleRentalSystem {
    private final List<Store> stores;

    public VehicleRentalSystem(List<Store> stores) {
        this.stores = stores;
    }

    public Reservation reserve(String reservationId, User user, Location pickupLocation, VehicleType vehicleType, int rentalDays) {
        Store store = selectStore(pickupLocation);
        Vehicle vehicle = store.findAvailableVehicle(vehicleType);
        if (vehicle == null) {
            throw new IllegalArgumentException("No vehicle available for type " + vehicleType);
        }
        vehicle.reserve();
        Reservation reservation = new Reservation(reservationId, user, vehicle, rentalDays);
        reservation.confirm();
        return reservation;
    }

    private Store selectStore(Location pickupLocation) {
        for (Store store : stores) {
            // Interview simplification: same-city match ko nearest store assume kar rahe hain.
            if (store.getLocation().getCity().equalsIgnoreCase(pickupLocation.getCity())) {
                return store;
            }
        }
        throw new IllegalArgumentException("No store found for city " + pickupLocation.getCity());
    }
}
