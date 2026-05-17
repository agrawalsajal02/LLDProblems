# Cricbuzz

This package is now simplified for LLD interview practice.

Core entities:
- `Match`
- `Innings`
- `Team`
- `Player`
- `BallOutcome`

What was removed from the earlier version:
- extra score updater observer classes
- separate batting/bowling controller classes
- tiny scorecard classes
- separate ball/over/wicket object explosion

How to remember the final design:
`Match -> 2 innings -> each innings loops balls -> outcome updates team + player stats`

Best interview explanation:
1. `Match` orchestrates innings
2. `Innings` handles ball-by-ball simulation
3. `Team` manages strike, next batter, and next bowler
4. `Player` stores batting and bowling stats

Hinglish memory line:
`match innings chalata hai, innings ball outcome handle karti hai, team strike aur batting order sambhalti hai`
