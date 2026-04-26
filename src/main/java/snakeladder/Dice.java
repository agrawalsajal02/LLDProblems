package snakeladder;

import java.util.Random;

public final class Dice {
    private final int diceCount;
    private final Random random = new Random();

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int roll() {
        int total = 0;
        for (int i = 0; i < diceCount; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
}
