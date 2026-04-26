# Liskov Substitution Principle

## Idea
- If `B` is used somewhere, a subtype `A` should be usable there without surprising breakage.

## Folder layout
- `bad`: electric car forced into wrong contract
- `good`: contract split so subtypes stay substitutable

## Use when
- You are evaluating inheritance or interface design.

## Do not misuse
- If subtype needs to throw "not supported", the abstraction is probably wrong.

## Memory hook
- "Subtype should not surprise the caller."
