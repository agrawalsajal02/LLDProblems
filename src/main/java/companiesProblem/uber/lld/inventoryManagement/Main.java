package companiesProblem.uber.lld.inventoryManagement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public final class Main {
    public static void main(String[] args) {
        CarInventoryService inventoryService = new CarInventoryService();
        inventoryService.addCar(
            new Car("CAR-1", "KA01AA1001", "Hyundai", "i20", "Bangalore", CarCategory.HATCHBACK,
                BigDecimal.valueOf(120), CarStatus.ACTIVE)
        );
        inventoryService.addCar(
            new Car("CAR-2", "KA01AA1002", "Honda", "City", "Bangalore", CarCategory.SEDAN,
                BigDecimal.valueOf(180), CarStatus.ACTIVE)
        );
        inventoryService.addCar(
            new Car("CAR-3", "KA01AA1003", "Maruti", "Dzire", "Bangalore", CarCategory.SEDAN,
                BigDecimal.valueOf(150), CarStatus.ACTIVE)
        );
        inventoryService.addCar(
            new Car("CAR-4", "KA01AA1004", "Toyota", "Innova", "Bangalore", CarCategory.SUV,
                BigDecimal.valueOf(250), CarStatus.IN_MAINTENANCE)
        );

        ReservationService reservationService = new ReservationService(
            inventoryService,
            new LowestPriceCarSelectionStrategy()
        );

        TimeSlot morningSlot = new TimeSlot(
            LocalDateTime.of(2026, 4, 10, 9, 0),
            LocalDateTime.of(2026, 4, 10, 13, 0)
        );

        List<Car> availableSedans = inventoryService.findAvailableCars(
            new CarSearchCriteria("Bangalore", CarCategory.SEDAN),
            morningSlot
        );
        System.out.println("Available sedans before booking: " + availableSedans);

        Reservation reservation = reservationService.createReservation(
            new ReservationRequest(
                "USER-101",
                new CarSearchCriteria("Bangalore", CarCategory.SEDAN),
                morningSlot
            )
        );
        System.out.println("Reservation created: " + reservation);

        List<Car> remainingSedans = inventoryService.findAvailableCars(
            new CarSearchCriteria("Bangalore", CarCategory.SEDAN),
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 0),
                LocalDateTime.of(2026, 4, 10, 12, 0)
            )
        );
        System.out.println("Available sedans after booking: " + remainingSedans);

        reservationService.cancelReservation(reservation.getReservationId());
        System.out.println("Reservation after cancellation: " +
            reservationService.getReservation(reservation.getReservationId()));
    }
}
