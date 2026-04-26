package tictactoe;

import java.util.ArrayDeque;

public final class Main {
    public static void main(String[] args) {
        ArrayDeque<Player> players = new ArrayDeque<>();
        players.add(new Player("Player1", PieceType.X));
        players.add(new Player("Player2", PieceType.O));

        Game game = new Game(new Board(3), players);
        System.out.println(game.playMove(0, 0));
        System.out.println(game.playMove(1, 0));
        System.out.println(game.playMove(0, 1));
        System.out.println(game.playMove(1, 1));
        System.out.println(game.playMove(0, 2));
    }
}
