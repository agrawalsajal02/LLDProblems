# SRP Violated

## Problem
- `BadBook` handles both book logic and printing.
- Now the class changes for content rules and also for output changes.

## Why this is bad
- Testing mixes unrelated concerns.
- Printing changes can affect domain code.

## Memory hook
- "Data + printing in one class means multiple reasons to change."
