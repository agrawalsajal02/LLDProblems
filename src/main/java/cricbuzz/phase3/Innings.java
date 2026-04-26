package cricbuzz.phase3;

public final class Innings {
    private final Team team;
    private final int maxOvers;
    private final int target;
    private final OutcomeGenerator outcomeGenerator;
    private int balls;

    public Innings(Team team, int maxOvers, int target, OutcomeGenerator outcomeGenerator) {
        this.team = team;
        this.maxOvers = maxOvers;
        this.target = target;
        this.outcomeGenerator = outcomeGenerator;
    }

    public void play() {
        team.reset();
        while (!isComplete()) {
            team.apply(outcomeGenerator.nextOutcome());
            balls++;
            if (balls % 6 == 0) {
                team.endOver();
            }
        }
    }

    public String scoreLine() {
        return team.getName() + " -> " + team.getTotalRuns() + "/" + team.getWickets() + " in " + (balls / 6) + "." + (balls % 6);
    }

    private boolean isComplete() {
        if (team.allOut()) return true;
        if (balls >= maxOvers * 6) return true;
        return target != -1 && team.getTotalRuns() > target;
    }
}
