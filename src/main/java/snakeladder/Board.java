package snakeladder;

import java.util.HashMap;
import java.util.Map;

public final class Board {
    private final int size;
    private final Map<Integer, Jump> jumps = new HashMap<>();

    public Board(int size) {
        this.size = size;
    }

    public int getWinningCell() {
        return size * size - 1;
    }

    public void addJump(Jump jump) {
        jumps.put(jump.getStart(), jump);
    }

    public int resolvePosition(int position) {
        Jump jump = jumps.get(position);
        return jump == null ? position : jump.getEnd();
    }
}
