# Connect Four LLD (Java)

This is a memory‑friendly guide so you only remember the logic, not every line of code.

**Package**: `connectfour`

## 1. Objects and Responsibilities
- `Game`: orchestrates turns and game state.
- `Board`: owns grid + placement + win checks.
- `Player`: name + disc color.
- `GameState`, `DiscColor`: enums.

## 2. Core Data You Must Track
- Board: `6 x 7` grid of `DiscColor` or `null`.
- Game: `currentPlayer`, `state`, `winner`.

## 3. Main Flow (the only flow to remember)
When a move happens:
1. Check game is `IN_PROGRESS`.
2. Check `player == currentPlayer`.
3. Place disc on board -> get `row` (or `-1` if invalid).
4. Check win from `(row, col)`.
5. If win -> `state = WON`, `winner = player`.
6. Else if board full -> `state = DRAW`.
7. Else switch turn.

That single pipeline drives the entire game.

## 4. Board Placement Logic
How a disc drops in a column:
1. Validate column bounds.
2. If top cell of column is full -> invalid.
3. Scan from bottom row up to find first empty cell.
4. Place disc and return the landing row.

## 5. Win Check Logic (direction vector pattern)
Only remember this idea:
- A win is **4 in a row** in any of 4 directions.
- Directions: `(0,1)`, `(1,0)`, `(1,1)`, `(-1,1)`.
- For each direction: count both forward and backward.
- If count >= 4 -> win.

Pseudo:
```
for each (dr, dc) in directions:
    count = 1
    count += countInDirection(dr, dc)
    count += countInDirection(-dr, -dc)
    if count >= 4: win
```

## 6. Minimal API You Should Be Able to Rebuild
- `Game.makeMove(player, col) -> boolean`
- `Game.getCurrentPlayer()`
- `Game.getGameState()`
- `Game.getWinner()`
- `Board.placeDisc(col, color) -> row or -1`
- `Board.checkWin(row, col, color)`

## 7. If You Forget Code, Reconstruct It From These Facts
- The board is 6 rows, 7 columns.
- A move always: validate -> place -> win/draw -> switch.
- Win check is always directional scanning.
- Player is just name + color.

That’s it. You can rebuild everything from these.
