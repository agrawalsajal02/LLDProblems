package connectfour.extensibility;

import java.util.ArrayDeque;
import java.util.Deque;

import connectfour.DiscColor;
import connectfour.GameState;
import connectfour.Player;

public class ExtensibleGame {
    private final ConfigurableBoard board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState state;
    private Player winner;
    private final Deque<Move> history;

    public ExtensibleGame(Player player1, Player player2, int rows, int cols) {
        this.board = new ConfigurableBoard(rows, cols);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = GameState.IN_PROGRESS;
        this.winner = null;
        this.history = new ArrayDeque<>();
    }

    public boolean makeMove(Player player, int column) {
        if (state != GameState.IN_PROGRESS) {
            return false;
        }
        if (player != currentPlayer) {
            return false;
        }

        DiscColor color = player.getColor();
        int row = board.placeDisc(column, color);
        if (row == -1) {
            return false;
        }

        history.push(new Move(player, row, column));

        if (board.checkWin(row, column, color)) {
            state = GameState.WON;
            winner = player;
        } else if (board.isFull()) {
            state = GameState.DRAW;
        } else {
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
        return true;
    }

    public boolean undoLastMove() {
        if (history.isEmpty()) {
            return false;
        }
        Move last = history.pop();
        board.clearCell(last.getRow(), last.getCol());
        currentPlayer = last.getPlayer();
        state = GameState.IN_PROGRESS;
        winner = null;
        return true;
    }

    public ConfigurableBoard getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getGameState() {
        return state;
    }

    public Player getWinner() {
        return winner;
    }
}
