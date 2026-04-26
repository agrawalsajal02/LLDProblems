# Connect Four Extensibility (Interview Notes)

This package shows how the base design can evolve with minimal changes.

## 1) Variable Board Size
- Use `ConfigurableBoard(rows, cols)`.
- All logic still works because it depends on `rows`, `cols`, and `inBounds`.

## 2) Undo / Move History
- `ExtensibleGame` keeps a stack of `Move`.
- Each successful move pushes a `Move(player, row, col)`.
- `undoLastMove()` pops, clears the cell, restores turn, resets state.

## 3) Computer Opponent
- `BotEngine.chooseMove(game)` scans for the first valid column.
- Game rules stay unchanged. Bot just chooses a column.

## Files
- `ConfigurableBoard.java` (variable size + clear cell)
- `Move.java` (value object for undo)
- `ExtensibleGame.java` (history + configurable board)
- `BotEngine.java` (simple AI)
- `ExtensibilityMain.java` (demo)
