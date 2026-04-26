package snakeladder;

import java.util.ArrayDeque;

public final class Main {
    public static void main(String[] args) {
        Board board = new Board(10);
        board.addJump(new Jump(2, 22));
        board.addJump(new Jump(18, 5));
        board.addJump(new Jump(20, 29));

        ArrayDeque<Player> players = new ArrayDeque<>();
        players.add(new Player("P1"));
        players.add(new Player("P2"));

        Game game = new Game(board, new Dice(1), players);
        Player winner = game.play();
        System.out.println("Winner is: " + winner.getId());
    }
}
