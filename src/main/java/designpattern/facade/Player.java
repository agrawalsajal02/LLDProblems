package designpattern.facade;

public class Player {
    private final String mark;

    public Player(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
