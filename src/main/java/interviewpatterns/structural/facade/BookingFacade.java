package interviewpatterns.structural.facade;

public class BookingFacade {
    private final MovieCatalog movieCatalog;
    private final SeatInventory seatInventory;
    private final PaymentService paymentService;

    public BookingFacade() {
        this.movieCatalog = new MovieCatalog();
        this.seatInventory = new SeatInventory();
        this.paymentService = new PaymentService();
    }

    public boolean bookTicket(String userId, String movieId, String showId, String seatId, int amount) {
        if (!movieCatalog.hasMovie(movieId)) {
            return false;
        }
        if (!seatInventory.isSeatAvailable(showId, seatId)) {
            return false;
        }
        if (!paymentService.charge(userId, amount)) {
            return false;
        }

        seatInventory.reserve(showId, seatId);
        return true;
    }
}
