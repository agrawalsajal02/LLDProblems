package cricbuzz;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    public static void main(String[] args) {
        Match match = new Match(createTeam("India"), createTeam("Australia"), MatchFormat.T20);
        match.start();
    }

    private static Team createTeam(String name) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            players.add(new Player(name + "-P" + i));
        }
        return new Team(name, players);
    }
}
