# Single Responsibility Principle

## Idea
- A class should have one responsibility.
- In practice: one main reason to change.

## Folder layout
- `bad`: one class doing book logic and printing
- `good`: data logic and printing split into separate classes

## Use when
- A class is mixing business rules with printing, persistence, transport, or UI concerns.

## Do not overdo
- SRP is not "one method per class".
- Split by reason-to-change, not by arbitrary size.

## Memory hook
- "One class, one reason to change."
