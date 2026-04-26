package cricbuzz;

public final class Innings {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final int maxOvers;
    private final int target;
    private final OutcomeGenerator outcomeGenerator;
    private int completedBalls;

    public Innings(Team battingTeam, Team bowlingTeam, int maxOvers, int target, OutcomeGenerator outcomeGenerator) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.maxOvers = maxOvers;
        this.target = target;
        this.outcomeGenerator = outcomeGenerator;
    }

    public void play() {
        battingTeam.resetForMatch();
        int ballsInCurrentOver = 0;
        Player currentBowler = bowlingTeam.chooseNextBowler();

        while (!isComplete()) {
            if (ballsInCurrentOver == 6) {
                ballsInCurrentOver = 0;
                battingTeam.swapStrike();
                currentBowler = bowlingTeam.chooseNextBowler();
            }

            BallOutcome outcome = outcomeGenerator.nextOutcome();
            playBall(outcome, currentBowler);
            completedBalls++;
            ballsInCurrentOver++;
        }
    }

    public String scoreLine() {
        return battingTeam.getTotalRuns() + "/" + battingTeam.getWickets() + " in " + (completedBalls / 6) + "." + (completedBalls % 6);
    }

    private void playBall(BallOutcome outcome, Player bowler) {
        Player striker = battingTeam.getStriker();
        striker.faceBall(outcome);
        bowler.bowlBall(outcome);

        battingTeam.addRuns(outcome.getRuns());

        if (outcome.isWicket()) {
            battingTeam.recordWicket();
            if (!battingTeam.allOut()) {
                battingTeam.sendNextBatter();
            }
            return;
        }

        // Odd runs par strike swap hoti hai kyunki batters running ke baad opposite ends par pahunch jaate hain.
        if (outcome.getRuns() % 2 == 1) {
            battingTeam.swapStrike();
        }
    }

    private boolean isComplete() {
        if (battingTeam.allOut()) {
            return true;
        }
        if (completedBalls >= maxOvers * 6) {
            return true;
        }
        // Chase me jaise hi target cross ho jaye, innings ko wahi rok do kyunki winner decide ho chuka hota hai.
        return target != -1 && battingTeam.getTotalRuns() > target;
    }
}
