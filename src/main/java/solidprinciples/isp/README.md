# Interface Segregation Principle

## Idea
- Clients should not be forced to depend on methods they do not use.

## Folder layout
- `bad`: one broad interface forcing unrelated methods
- `good`: split interfaces by role

## Use when
- Implementers need only subsets of a large interface.

## Do not misuse
- Do not split interfaces mechanically with no domain meaning.

## Memory hook
- "Client should depend only on methods it actually needs."
