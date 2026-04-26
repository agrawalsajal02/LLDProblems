package companiesProblem.uber.lld.inventoryManagement;

import java.math.BigDecimal;

public final class Reservation {
    private final String reservationId;
    private final String userId;
    private final Car car;
    private final TimeSlot reservedSlot;
    private final BigDecimal estimatedCharge;
    private ReservationStatus status;

    public Reservation(
        String reservationId,
        String userId,
        Car car,
        TimeSlot reservedSlot,
        BigDecimal estimatedCharge,
        ReservationStatus status
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.car = car;
        this.reservedSlot = reservedSlot;
        this.estimatedCharge = estimatedCharge;
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public Car getCar() {
        return car;
    }

    public TimeSlot getReservedSlot() {
        return reservedSlot;
    }

    public BigDecimal getEstimatedCharge() {
        return estimatedCharge;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void updateStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
            "reservationId='" + reservationId + '\'' +
            ", userId='" + userId + '\'' +
            ", carId='" + car.getCarId() + '\'' +
            ", reservedSlot=" + reservedSlot +
            ", estimatedCharge=" + estimatedCharge +
            ", status=" + status +
            '}';
    }
}
