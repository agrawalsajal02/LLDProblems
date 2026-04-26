package cricbuzz.phase1;

public final class TeamScore {
    private final String teamName;
    private int runs;
    private int wickets;

    public TeamScore(String teamName) {
        this.teamName = teamName;
    }

    public void apply(BallOutcome outcome) {
        runs += outcome.getRuns();
        if (outcome.isWicket()) {
            wickets++;
        }
    }

    public String scoreLine(int balls) {
        return teamName + " -> " + runs + "/" + wickets + " in " + (balls / 6) + "." + (balls % 6);
    }
}
