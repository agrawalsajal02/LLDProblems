# DIP Violated

## Problem
- `Windows98Machine` directly creates `StandardKeyboard` and `Monitor`.
- High-level policy is tightly coupled to low-level concrete classes.

## Why this is bad
- Harder to test
- Harder to replace implementations
- High-level module knows too much about construction details

## Memory hook
- "If high-level class uses `new` for concrete dependencies, coupling grows."
