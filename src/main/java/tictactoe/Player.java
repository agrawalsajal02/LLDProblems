package tictactoe;

public final class Player {
    private final String name;
    private final PieceType pieceType;

    public Player(String name, PieceType pieceType) {
        this.name = name;
        this.pieceType = pieceType;
    }

    public String getName() {
        return name;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
