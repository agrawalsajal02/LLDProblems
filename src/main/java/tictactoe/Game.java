package tictactoe;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Game {
    private final Board board;
    private final Deque<Player> turnOrder;

    public Game(Board board, Deque<Player> turnOrder) {
        this.board = board;
        this.turnOrder = turnOrder;
    }

    public String playMove(int row, int col) {
        Player current = turnOrder.removeFirst();
        if (!board.place(row, col, current.getPieceType())) {
            turnOrder.addFirst(current);
            throw new IllegalArgumentException("Invalid move");
        }
        if (hasWinner(row, col, current.getPieceType())) {
            return current.getName();
        }
        turnOrder.addLast(current);
        return board.isFull() ? "DRAW" : "CONTINUE";
    }

    private boolean hasWinner(int row, int col, PieceType pieceType) {
        // Last move based check kar rahe hain kyunki winner sirf us row, column ya diagonal me aa sakta hai jahan naya move dala gaya hai.
        return isWinningRow(row, pieceType)
            || isWinningColumn(col, pieceType)
            || (row == col && isWinningMainDiagonal(pieceType))
            || (row + col == board.size() - 1 && isWinningAntiDiagonal(pieceType));
    }

    private boolean isWinningRow(int row, PieceType pieceType) {
        for (int col = 0; col < board.size(); col++) {
            if (board.get(row, col) != pieceType) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningColumn(int col, PieceType pieceType) {
        for (int row = 0; row < board.size(); row++) {
            if (board.get(row, col) != pieceType) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningMainDiagonal(PieceType pieceType) {
        for (int index = 0; index < board.size(); index++) {
            if (board.get(index, index) != pieceType) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningAntiDiagonal(PieceType pieceType) {
        for (int row = 0; row < board.size(); row++) {
            int col = board.size() - 1 - row;
            if (board.get(row, col) != pieceType) {
                return false;
            }
        }
        return true;
    }
}
