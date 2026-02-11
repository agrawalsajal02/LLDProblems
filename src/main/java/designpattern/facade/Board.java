package designpattern.facade;

public class Board {
    public boolean placeMark(int row, int col, String mark) {
        System.out.println("Placed " + mark + " at (" + row + "," + col + ")");
        return true;
    }

    public boolean checkWin(int row, int col) {
        return false;
    }

    public boolean isFull() {
        return false;
    }
}
