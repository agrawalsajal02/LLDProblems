package bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BookingService {
    public Booking bookSeats(String bookingId, Theatre theatre, Show show, List<Integer> requestedSeatIds) {
        validateShowBelongsToTheatre(theatre, show);

        // HashSet-based availability check use kar rahe hain taaki booked seat lookup O(1) rahe aur race-free logic explain karna easy ho.
        for (int seatId : requestedSeatIds) {
            if (show.getBookedSeatIds().contains(seatId)) {
                throw new IllegalArgumentException("Seat already booked: " + seatId);
            }
        }

        validateSeatIdsExistOnScreen(show, requestedSeatIds);

        for (int seatId : requestedSeatIds) {
            show.getBookedSeatIds().add(seatId);
        }

        return new Booking(bookingId, theatre, show, new ArrayList<>(requestedSeatIds));
    }

    public Show findFirstShow(List<Theatre> theatres, City city, String movieName) {
        for (Theatre theatre : theatres) {
            if (theatre.getCity() != city) {
                continue;
            }
            for (Show show : theatre.getShows()) {
                if (show.getMovie().getName().equalsIgnoreCase(movieName)) {
                    return show;
                }
            }
        }
        throw new IllegalArgumentException("No matching show found");
    }

    public List<Seat> getAvailableSeats(Show show) {
        return show.getScreen().getSeats().stream()
            .filter(seat -> !show.getBookedSeatIds().contains(seat.getSeatId()))
            .collect(Collectors.toList());
    }

    private void validateShowBelongsToTheatre(Theatre theatre, Show show) {
        if (!theatre.getScreens().contains(show.getScreen())) {
            throw new IllegalArgumentException("Selected screen does not belong to theatre " + theatre.getId());
        }
        if (!show.getScreen().getShows().contains(show)) {
            throw new IllegalArgumentException("Show is not scheduled on selected screen " + show.getScreen().getId());
        }
    }

    private void validateSeatIdsExistOnScreen(Show show, List<Integer> requestedSeatIds) {
        for (int seatId : requestedSeatIds) {
            boolean exists = show.getScreen().getSeats().stream().anyMatch(seat -> seat.getSeatId() == seatId);
            if (!exists) {
                throw new IllegalArgumentException("Seat does not exist on selected screen: " + seatId);
            }
        }
    }
}
