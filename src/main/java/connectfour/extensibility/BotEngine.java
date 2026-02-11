package connectfour.extensibility;

public class BotEngine {
    public int chooseMove(ExtensibleGame game) {
        ConfigurableBoard board = game.getBoard();
        for (int col = 0; col < board.getCols(); col++) {
            if (board.canPlace(col)) {
                return col;
            }
        }
        return -1;
    }
}
