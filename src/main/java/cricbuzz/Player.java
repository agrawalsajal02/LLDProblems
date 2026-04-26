package cricbuzz;

public final class Player {
    private final String name;
    private int runs;
    private int ballsFaced;
    private int wicketsTaken;
    private int ballsBowled;
    private int runsConceded;
    private boolean out;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRuns() {
        return runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public boolean isOut() {
        return out;
    }

    public void faceBall(BallOutcome outcome) {
        ballsFaced++;
        runs += outcome.getRuns();
        if (outcome.isWicket()) {
            out = true;
        }
    }

    public void bowlBall(BallOutcome outcome) {
        ballsBowled++;
        runsConceded += outcome.getRuns();
        if (outcome.isWicket()) {
            wicketsTaken++;
        }
    }
}
