package cricbuzz;

import java.util.ArrayList;
import java.util.List;

public final class Team {
    private final String name;
    private final List<Player> players;
    private int totalRuns;
    private int wickets;
    private int nextBatterIndex;
    private int strikerIndex;
    private int nonStrikerIndex;
    private int nextBowlerIndex;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = new ArrayList<>(players);
        resetForMatch();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWickets() {
        return wickets;
    }

    public void resetForMatch() {
        totalRuns = 0;
        wickets = 0;
        nextBatterIndex = 2;
        strikerIndex = 0;
        nonStrikerIndex = 1;
        nextBowlerIndex = 0;
    }

    public Player getStriker() {
        return players.get(strikerIndex);
    }

    public Player getNonStriker() {
        return players.get(nonStrikerIndex);
    }

    public void addRuns(int runs) {
        totalRuns += runs;
    }

    public void recordWicket() {
        wickets++;
    }

    public boolean allOut() {
        return wickets >= players.size() - 1 || nextBatterIndex >= players.size() && getStriker().isOut();
    }

    public void sendNextBatter() {
        if (nextBatterIndex >= players.size()) {
            return;
        }
        strikerIndex = nextBatterIndex++;
    }

    public void swapStrike() {
        int temp = strikerIndex;
        strikerIndex = nonStrikerIndex;
        nonStrikerIndex = temp;
    }

    public Player chooseNextBowler() {
        int bowlerPoolStart = Math.max(0, players.size() - 4);
        int bowlerPoolSize = players.size() - bowlerPoolStart;
        Player bowler = players.get(bowlerPoolStart + (nextBowlerIndex % bowlerPoolSize));
        nextBowlerIndex++;
        // Interview simplification: last 4 players ko bowling pool maan kar cyclic rotation se next bowler choose kar rahe hain.
        return bowler;
    }
}
