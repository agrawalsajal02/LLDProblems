package bookmyshow;

import java.util.ArrayList;
import java.util.List;

public final class Screen {
    private final String id;
    private final List<Seat> seats = new ArrayList<>();
    private final List<Show> shows = new ArrayList<>();

    public Screen(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void addShow(Show show) {
        // Screen physical hall hai, isliye us par chalne wale scheduled shows ko isi level par maintain karna zyada natural hai.
        shows.add(show);
    }
}
