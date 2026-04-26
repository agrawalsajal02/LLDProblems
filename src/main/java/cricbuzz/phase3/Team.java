package cricbuzz.phase3;

import java.util.ArrayList;
import java.util.List;

public final class Team {
    private final String name;
    private final List<Player> players;
    private int totalRuns;
    private int wickets;
    private int strikerIndex;
    private int nonStrikerIndex;
    private int nextBatterIndex;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = new ArrayList<>(players);
        reset();
    }

    public void reset() {
        totalRuns = 0;
        wickets = 0;
        strikerIndex = 0;
        nonStrikerIndex = 1;
        nextBatterIndex = 2;
    }

    public String getName() {
        return name;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWickets() {
        return wickets;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void apply(BallOutcome outcome) {
        players.get(strikerIndex).faceBall(outcome);
        totalRuns += outcome.getRuns();
        if (outcome.isWicket()) {
            wickets++;
            if (nextBatterIndex < players.size()) {
                strikerIndex = nextBatterIndex++;
            }
            return;
        }
        if (outcome.getRuns() % 2 == 1) {
            swapStrike();
        }
    }

    public void endOver() {
        swapStrike();
    }

    public boolean allOut() {
        return wickets >= players.size() - 1;
    }

    private void swapStrike() {
        int temp = strikerIndex;
        strikerIndex = nonStrikerIndex;
        nonStrikerIndex = temp;
    }
}
