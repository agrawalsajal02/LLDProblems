package connectfour;

public final class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;

    private final DiscColor[][] grid;

    public Board() {
        this.grid = new DiscColor[ROWS][COLS];
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    public DiscColor getCell(int row, int col) {
        if (!inBounds(row, col)) {
            return null;
        }
        return grid[row][col];
    }

    public boolean canPlace(int col) {
        if (col < 0 || col >= COLS) {
            return false;
        }
        return grid[0][col] == null;
    }

    // Returns the row where the disc lands, or -1 if invalid.
    public int placeDisc(int col, DiscColor color) {
        if (col < 0 || col >= COLS) {
            return -1;
        }
        if (!canPlace(col)) {
            return -1;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == null) {
                grid[row][col] = color;
                return row;
            }
        }
        return -1;
    }

    public boolean isFull() {
        for (int c = 0; c < COLS; c++) {
            if (canPlace(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(int row, int col, DiscColor color) {
        if (!inBounds(row, col)) {
            return false;
        }
        if (grid[row][col] != color) {
            return false;
        }

        int[][] directions = {
            {0, 1},   // horizontal
            {1, 0},   // vertical
            {1, 1},   // diagonal down-right
            {-1, 1}   // diagonal up-right
        };

        for (int[] d : directions) {
            int count = 1;
            count += countInDirection(row, col, d[0], d[1], color);
            count += countInDirection(row, col, -d[0], -d[1], color);
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private int countInDirection(int row, int col, int dr, int dc, DiscColor color) {
        int count = 0;
        int r = row + dr;
        int c = col + dc;

        while (inBounds(r, c) && grid[r][c] == color) {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
}
