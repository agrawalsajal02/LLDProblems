package amazonlocker.extensibility;

public class Reservation {
    private final String reservationId;
    private final ExtCompartment compartment;

    public Reservation(String reservationId, ExtCompartment compartment) {
        this.reservationId = reservationId;
        this.compartment = compartment;
    }

    public String getReservationId() {
        return reservationId;
    }

    public ExtCompartment getCompartment() {
        return compartment;
    }
}
