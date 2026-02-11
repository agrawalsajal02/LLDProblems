package connectfour.extensibility;

import connectfour.DiscColor;

public class ConfigurableBoard {
    private final int rows;
    private final int cols;
    private final DiscColor[][] grid;

    public ConfigurableBoard(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and cols must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.grid = new DiscColor[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public DiscColor getCell(int row, int col) {
        if (!inBounds(row, col)) {
            return null;
        }
        return grid[row][col];
    }

    public boolean canPlace(int col) {
        if (col < 0 || col >= cols) {
            return false;
        }
        return grid[0][col] == null;
    }

    // Returns the row where the disc lands, or -1 if invalid.
    public int placeDisc(int col, DiscColor color) {
        if (col < 0 || col >= cols) {
            return -1;
        }
        if (!canPlace(col)) {
            return -1;
        }
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][col] == null) {
                grid[row][col] = color;
                return row;
            }
        }
        return -1;
    }

    public void clearCell(int row, int col) {
        if (inBounds(row, col)) {
            grid[row][col] = null;
        }
    }

    public boolean isFull() {
        for (int c = 0; c < cols; c++) {
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
            {0, 1},
            {1, 0},
            {1, 1},
            {-1, 1}
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
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
