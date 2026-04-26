package cricbuzz;

import java.util.Random;

public final class OutcomeGenerator {
    private final Random random = new Random();

    public BallOutcome nextOutcome() {
        int value = random.nextInt(100);
        if (value < 10) {
            return BallOutcome.WICKET;
        }
        if (value < 30) {
            return BallOutcome.ONE;
        }
        if (value < 45) {
            return BallOutcome.TWO;
        }
        if (value < 60) {
            return BallOutcome.FOUR;
        }
        if (value < 68) {
            return BallOutcome.SIX;
        }
        return BallOutcome.DOT;
    }
}
