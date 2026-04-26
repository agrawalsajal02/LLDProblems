package cricbuzz.phase3;

public final class Match {
    private final Team teamA;
    private final Team teamB;

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public void start() {
        OutcomeGenerator generator = new OutcomeGenerator();
        Innings first = new Innings(teamA, MatchFormat.T20.getOversPerInnings(), -1, generator);
        first.play();
        Innings second = new Innings(teamB, MatchFormat.T20.getOversPerInnings(), teamA.getTotalRuns(), generator);
        second.play();

        System.out.println(first.scoreLine());
        System.out.println(second.scoreLine());
        System.out.println("Winner: " + (teamB.getTotalRuns() > teamA.getTotalRuns() ? teamB.getName() : teamA.getName()));
    }
}
