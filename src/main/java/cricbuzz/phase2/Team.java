package cricbuzz.phase2;

import java.util.ArrayList;
import java.util.List;

public final class Team {
    private final String name;
    private final List<Player> players;
    private int totalRuns;
    private int wickets;
    private int strikerIndex = 0;
    private int nonStrikerIndex = 1;
    private int nextBatterIndex = 2;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = new ArrayList<>(players);
    }

    public Player getStriker() {
        return players.get(strikerIndex);
    }

    public void apply(BallOutcome outcome) {
        getStriker().faceBall(outcome);
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

    public String scoreLine(int balls) {
        return name + " -> " + totalRuns + "/" + wickets + " in " + (balls / 6) + "." + (balls % 6);
    }

    public void printBattingScorecard() {
        for (Player player : players) {
            System.out.println(player.scoreLine());
        }
    }

    private void swapStrike() {
        int temp = strikerIndex;
        strikerIndex = nonStrikerIndex;
        nonStrikerIndex = temp;
    }
}
