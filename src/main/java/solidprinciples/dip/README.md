# Dependency Inversion Principle

## Idea
- High-level modules should not depend on low-level concrete classes.
- Both should depend on abstractions.

## Folder layout
- `bad`: high-level class constructs low-level dependencies directly
- `good`: high-level class depends on abstractions and receives dependencies from outside

## Use when
- You want decoupling, easy testing, and easy swapping of implementations.

## Do not misuse
- Do not invent interfaces when there is no real variation point or seam.

## Memory hook
- "Depend on abstraction, not on concrete details."
