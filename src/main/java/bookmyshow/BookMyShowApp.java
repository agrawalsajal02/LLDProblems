package bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BookMyShowApp {
    private final List<Theatre> theatres = new ArrayList<>();
    private final BookingService bookingService = new BookingService();

    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    public List<Movie> getMoviesByCity(City city) {
        List<Movie> movies = new ArrayList<>();
        for (Theatre theatre : theatres) {
            if (theatre.getCity() != city) {
                continue;
            }
            for (Show show : theatre.getShows()) {
                boolean alreadyPresent = movies.stream().anyMatch(movie -> movie.getId().equals(show.getMovie().getId()));
                if (!alreadyPresent) {
                    movies.add(show.getMovie());
                }
            }
        }
        return movies;
    }

    public List<Theatre> getTheatresByMovie(City city, String movieId) {
        return theatres.stream()
            .filter(theatre -> theatre.getCity() == city)
            .filter(theatre -> theatre.getShows().stream().anyMatch(show -> show.getMovie().getId().equals(movieId)))
            .collect(Collectors.toList());
    }

    public List<Show> getShows(Theatre theatre, String movieId) {
        return theatre.getShows().stream()
            .filter(show -> show.getMovie().getId().equals(movieId))
            .collect(Collectors.toList());
    }

    public List<Seat> getAvailableSeats(Show show) {
        return bookingService.getAvailableSeats(show);
    }

    public Booking bookSeats(String bookingId, Theatre theatre, Show show, List<Integer> seatIds) {
        return bookingService.bookSeats(bookingId, theatre, show, seatIds);
    }
}
