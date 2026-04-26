package bookmyshow;

import java.util.HashSet;
import java.util.Set;

public final class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final int startHour;
    private final Set<Integer> bookedSeatIds = new HashSet<>();

    public Show(String id, Movie movie, Screen screen, int startHour) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startHour = startHour;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getStartHour() {
        return startHour;
    }

    public Set<Integer> getBookedSeatIds() {
        return bookedSeatIds;
    }
}
