package bookmyshow;

import java.util.List;

public final class Booking {
    private final String bookingId;
    private final Theatre theatre;
    private final Show show;
    private final List<Integer> seatIds;

    public Booking(String bookingId, Theatre theatre, Show show, List<Integer> seatIds) {
        this.bookingId = bookingId;
        this.theatre = theatre;
        this.show = show;
        this.seatIds = seatIds;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Show getShow() {
        return show;
    }

    public List<Integer> getSeatIds() {
        return seatIds;
    }
}
