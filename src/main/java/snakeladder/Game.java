package snakeladder;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Game {
    private final Board board;
    private final Dice dice;
    private final Deque<Player> players;

    public Game(Board board, Dice dice, Deque<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
    }

    public Player play() {
        while (true) {
            // Queue rotation use kar rahe hain taaki remove-first + add-last se turn order naturally maintain ho jaye.
            Player current = players.removeFirst();
            int rolled = dice.roll();
            int nextPosition = current.getPosition() + rolled;

            if (nextPosition <= board.getWinningCell()) {
                current.setPosition(board.resolvePosition(nextPosition));
            }
            if (current.getPosition() == board.getWinningCell()) {
                return current;
            }
            players.addLast(current);
        }
    }
}
