package cricbuzz.phase2;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            players.add(new Player("India-P" + i));
        }
        new Innings(new Team("India", players), 2, new OutcomeGenerator()).play();
    }
}
