package companiesProblem.uber.lld.inventoryManagement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ReservationService {
    private final CarInventoryService carInventoryService;
    private final CarSelectionStrategy carSelectionStrategy;
    private final Map<String, Reservation> reservationsById;

    public ReservationService(CarInventoryService carInventoryService, CarSelectionStrategy carSelectionStrategy) {
        this.carInventoryService = carInventoryService;
        this.carSelectionStrategy = carSelectionStrategy;
        this.reservationsById = new HashMap<>();
    }

    public Reservation createReservation(ReservationRequest request) {
        List<Car> availableCars = carInventoryService.findAvailableCars(
            request.getSearchCriteria(),
            request.getRequestedSlot()
        );

        Car selectedCar = carSelectionStrategy.choose(availableCars);
        if (selectedCar == null) {
            throw new ReservationConflictException("No car available for the requested slot");
        }

        Reservation reservation = new Reservation(
            UUID.randomUUID().toString(),
            request.getUserId(),
            selectedCar,
            request.getRequestedSlot(),
            calculateEstimatedCharge(selectedCar, request.getRequestedSlot()),
            ReservationStatus.CONFIRMED
        );

        carInventoryService.blockCarForReservation(reservation);
        reservationsById.put(reservation.getReservationId(), reservation);
        return reservation;
    }

    public Reservation cancelReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new ReservationConflictException("Only confirmed reservations can be cancelled");
        }
        reservation.updateStatus(ReservationStatus.CANCELLED);
        return reservation;
    }

    public Reservation completeReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new ReservationConflictException("Only confirmed reservations can be completed");
        }
        reservation.updateStatus(ReservationStatus.COMPLETED);
        return reservation;
    }

    public Reservation getReservation(String reservationId) {
        Reservation reservation = reservationsById.get(reservationId);
        if (reservation == null) {
            throw new NotFoundException("Reservation not found: " + reservationId);
        }
        return reservation;
    }

    private BigDecimal calculateEstimatedCharge(Car car, TimeSlot requestedSlot) {
        long minutes = Duration.between(requestedSlot.getStart(), requestedSlot.getEnd()).toMinutes();
        BigDecimal hours = BigDecimal.valueOf(minutes)
            .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        return car.getHourlyRate().multiply(hours).setScale(2, RoundingMode.HALF_UP);
    }
}
