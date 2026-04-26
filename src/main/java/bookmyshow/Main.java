package bookmyshow;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
        Movie bahubali = new Movie("M1", "BAHUBALI");
        Theatre theatre = new Theatre("T1", City.BANGALORE);
        Screen screen = new Screen("S1");
        for (int i = 1; i <= 10; i++) {
            screen.addSeat(new Seat(i, i <= 4 ? SeatCategory.SILVER : SeatCategory.GOLD));
        }
        theatre.addScreen(screen);

        // Show ek scheduled event hai jo theatre ke existing screen ko use karta hai.
        Show show = new Show("SH1", bahubali, screen, 18);
        screen.addShow(show);

        BookMyShowApp app = new BookMyShowApp();
        app.addTheatre(theatre);

        List<Movie> movies = app.getMoviesByCity(City.BANGALORE);
        Theatre selectedTheatre = app.getTheatresByMovie(City.BANGALORE, bahubali.getId()).get(0);
        Show selectedShow = app.getShows(selectedTheatre, bahubali.getId()).get(0);

        System.out.println("Movies in Bangalore: " + movies.get(0).getName());
        System.out.println("Available seats before booking: " + app.getAvailableSeats(selectedShow).size());

        Booking booking = app.bookSeats("B1", selectedTheatre, selectedShow, List.of(2, 3));

        System.out.println("Booked theatre: " + booking.getTheatre().getId());
        System.out.println("Booked show: " + booking.getShow().getMovie().getName());
        System.out.println("Booked seats: " + booking.getSeatIds());
    }
}
