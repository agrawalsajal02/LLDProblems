package designpattern.facade;

public final class FacadeMain {
    public static void main(String[] args) {
        Game game = new Game();
        game.makeMove(0, 0);
        game.makeMove(1, 1);
    }
}
