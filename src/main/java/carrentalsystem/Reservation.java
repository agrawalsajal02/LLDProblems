package carrentalsystem;

public final class Reservation {
    private final String id;
    private final User user;
    private final Vehicle vehicle;
    private final int rentalDays;
    private ReservationStatus status;

    public Reservation(String id, User user, Vehicle vehicle, int rentalDays) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.status = ReservationStatus.CREATED;
    }

    public void confirm() {
        status = ReservationStatus.CONFIRMED;
    }

    public int getBillAmount() {
        return rentalDays * vehicle.getPricePerDay();
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
