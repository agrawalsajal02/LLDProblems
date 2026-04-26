package cricbuzz.phase2;

public final class Innings {
    private final Team team;
    private final int maxOvers;
    private final OutcomeGenerator outcomeGenerator;

    public Innings(Team team, int maxOvers, OutcomeGenerator outcomeGenerator) {
        this.team = team;
        this.maxOvers = maxOvers;
        this.outcomeGenerator = outcomeGenerator;
    }

    public void play() {
        int balls = 0;
        while (balls < maxOvers * 6 && !team.allOut()) {
            team.apply(outcomeGenerator.nextOutcome());
            balls++;
            if (balls % 6 == 0) {
                // Over khatam hone par strike swap hoti hai, isliye is step ko team helper me isolate kiya hai.
                team.endOver();
            }
        }
        System.out.println(team.scoreLine(balls));
        team.printBattingScorecard();
    }
}
