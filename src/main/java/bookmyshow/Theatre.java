package bookmyshow;

import java.util.ArrayList;
import java.util.List;

public final class Theatre {
    private final String id;
    private final City city;
    private final List<Screen> screens = new ArrayList<>();

    public Theatre(String id, City city) {
        this.id = id;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public List<Show> getShows() {
        List<Show> shows = new ArrayList<>();
        for (Screen screen : screens) {
            shows.addAll(screen.getShows());
        }
        return shows;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
