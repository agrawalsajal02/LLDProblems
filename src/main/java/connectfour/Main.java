package connectfour;

public final class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Player1", DiscColor.RED);
        Player p2 = new Player("Player2", DiscColor.YELLOW);
        Game game = new Game(p1, p2);

        // Minimal driver: demonstrate a few moves
        game.makeMove(game.getCurrentPlayer(), 0);
        game.makeMove(game.getCurrentPlayer(), 1);
        game.makeMove(game.getCurrentPlayer(), 0);
        game.makeMove(game.getCurrentPlayer(), 1);

        System.out.println("State: " + game.getGameState());
        System.out.println("Current: " + game.getCurrentPlayer().getName());
    }
}
