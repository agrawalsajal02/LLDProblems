# ISP Violated

## Problem
- `BearKeeper` is too broad.
- `BearCarer` is forced to implement `petTheBear()` even though it should not.

## Why this is bad
- You get fake implementations or unsupported-operation errors.
- Interfaces stop representing clear roles.

## Memory hook
- "Big interface forces unwanted behavior."
