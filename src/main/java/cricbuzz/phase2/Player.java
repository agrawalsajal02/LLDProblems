package cricbuzz.phase2;

public final class Player {
    private final String name;
    private int runs;
    private int ballsFaced;
    private boolean out;

    public Player(String name) {
        this.name = name;
    }

    public void faceBall(BallOutcome outcome) {
        ballsFaced++;
        runs += outcome.getRuns();
        if (outcome.isWicket()) {
            out = true;
        }
    }

    public String scoreLine() {
        return name + " -> " + runs + "(" + ballsFaced + ") out=" + out;
    }
}
