package cricbuzz;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Match {
    private final Team teamA;
    private final Team teamB;
    private final MatchFormat format;
    private final OutcomeGenerator outcomeGenerator;

    public Match(Team teamA, Team teamB, MatchFormat format) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.format = format;
        this.outcomeGenerator = new OutcomeGenerator();
    }

    public void start() {
        Team battingFirst = toss();
        Team bowlingFirst = battingFirst == teamA ? teamB : teamA;

        Innings firstInnings = new Innings(battingFirst, bowlingFirst, format.getOversPerInnings(), -1, outcomeGenerator);
        firstInnings.play();
        int target = battingFirst.getTotalRuns();

        Innings secondInnings = new Innings(bowlingFirst, battingFirst, format.getOversPerInnings(), target, outcomeGenerator);
        secondInnings.play();

        System.out.println("1st innings: " + battingFirst.getName() + " -> " + firstInnings.scoreLine());
        printScoreCard(battingFirst);
        System.out.println("2nd innings: " + bowlingFirst.getName() + " -> " + secondInnings.scoreLine());
        printScoreCard(bowlingFirst);

        Team winner = bowlingFirst.getTotalRuns() > battingFirst.getTotalRuns() ? bowlingFirst : battingFirst;
        System.out.println("Winner: " + winner.getName());
    }

    private Team toss() {
//        int value = ThreadLocalRandom.current().nextInt();
        return new Random().nextBoolean() ? teamA : teamB;
    }

    private void printScoreCard(Team team) {
        System.out.println("Batting scorecard for " + team.getName());
        for (Player player : team.getPlayers()) {
            System.out.println(player.getName() + " -> runs: " + player.getRuns() + ", balls: " + player.getBallsFaced() + ", out: " + player.isOut());
        }
    }
}
