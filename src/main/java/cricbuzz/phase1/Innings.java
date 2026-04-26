package cricbuzz.phase1;

public final class Innings {
    private final TeamScore score;
    private final int maxOvers;
    private final OutcomeGenerator outcomeGenerator;

    public Innings(TeamScore score, int maxOvers, OutcomeGenerator outcomeGenerator) {
        this.score = score;
        this.maxOvers = maxOvers;
        this.outcomeGenerator = outcomeGenerator;
    }

    public String play() {
        int balls = 0;
        int wickets = 0;
        while (balls < maxOvers * 6 && wickets < 10) {
            BallOutcome outcome = outcomeGenerator.nextOutcome();
            score.apply(outcome);
            balls++;
            if (outcome.isWicket()) {
                wickets++;
            }
        }
        return score.scoreLine(balls);
    }
}
