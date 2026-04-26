package tictactoe;

public final class Board {
    private final PieceType[][] cells;

    public Board(int size) {
        this.cells = new PieceType[size][size];
    }

    public int size() {
        return cells.length;
    }

    public boolean place(int row, int col, PieceType pieceType) {
        if (row < 0 || col < 0 || row >= size() || col >= size() || cells[row][col] != null) {
            return false;
        }
        cells[row][col] = pieceType;
        return true;
    }

    public PieceType get(int row, int col) {
        return cells[row][col];
    }

    public boolean isFull() {
        for (PieceType[] row : cells) {
            for (PieceType cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
