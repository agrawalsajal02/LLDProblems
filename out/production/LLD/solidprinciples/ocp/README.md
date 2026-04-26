# Open/Closed Principle

## Idea
- Open for extension, closed for modification.
- Add new behavior without breaking stable code.

## Use when
- New behavior keeps getting added to the same flow.
- You want to avoid reopening stable logic every time a new variant arrives.

## Do not misuse
- Do not use inheritance by default just because someone said "extend the class".
- Do not add abstraction before you see real variation points.
- Bug fixes still require modifying code; OCP is not "never touch existing code".

## Folder layout
- `bad`: example that violates OCP by growing `if/else` branches
- `good`: example that follows OCP using interface + composition

## What usually happens in real systems
- OCP is more commonly implemented using abstractions and composition.
- Inheritance is useful sometimes, but it is not the default answer for extensibility.

## Memory hook
- "New type? Add a new class, do not keep editing the old decision logic."
