package cricbuzz;

public enum BallOutcome {
    DOT(0, false),
    ONE(1, false),
    TWO(2, false),
    FOUR(4, false),
    SIX(6, false),
    WICKET(0, true);

    private final int runs;
    private final boolean wicket;

    BallOutcome(int runs, boolean wicket) {
        this.runs = runs;
        this.wicket = wicket;
    }

    public int getRuns() {
        return runs;
    }

    public boolean isWicket() {
        return wicket;
    }
}
