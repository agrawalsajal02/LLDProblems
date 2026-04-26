package companiesProblem.uber.lld.inventoryManagement;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class CarAvailabilityCalendar {
    private final Car car;
    private final NavigableMap<LocalDateTime, Reservation> reservationsByStartTime;

    public CarAvailabilityCalendar(Car car) {
        this.car = car;
        this.reservationsByStartTime = new TreeMap<>();
    }

    public Car getCar() {
        return car;
    }

    public boolean isAvailable(TimeSlot requestedSlot) {
        if (car.getStatus() != CarStatus.ACTIVE) {
            return false;
        }

        Map.Entry<LocalDateTime, Reservation> floor = reservationsByStartTime.floorEntry(requestedSlot.getStart());
        if (isActiveReservation(floor) && floor.getValue().getReservedSlot().overlaps(requestedSlot)) {
            return false;
        }

        Map.Entry<LocalDateTime, Reservation> ceiling = reservationsByStartTime.ceilingEntry(requestedSlot.getStart());
        return !isActiveReservation(ceiling) || !ceiling.getValue().getReservedSlot().overlaps(requestedSlot);
    }

    public void addReservation(Reservation reservation) {
        reservationsByStartTime.put(reservation.getReservedSlot().getStart(), reservation);
    }

    private boolean isActiveReservation(Map.Entry<LocalDateTime, Reservation> entry) {
        return entry != null && entry.getValue().getStatus() == ReservationStatus.CONFIRMED;
    }
}
