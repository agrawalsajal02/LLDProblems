package connectfour.extensibility;

import connectfour.DiscColor;
import connectfour.Player;

public final class ExtensibilityMain {
    public static void main(String[] args) {
        Player human = new Player("Human", DiscColor.RED);
        Player botPlayer = new Player("Bot", DiscColor.YELLOW);
        ExtensibleGame game = new ExtensibleGame(human, botPlayer, 6, 7);
        BotEngine bot = new BotEngine();

        game.makeMove(game.getCurrentPlayer(), 0);
        int botMove = bot.chooseMove(game);
        game.makeMove(game.getCurrentPlayer(), botMove);

        game.undoLastMove();
    }
}
