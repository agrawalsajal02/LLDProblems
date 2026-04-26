# DIP Followed

## Design
- `Windows98Machine` depends on `Keyboard` abstraction.
- Concrete dependency is provided from outside.

## Why this is better
- Easy to swap implementation
- Easier to test with fake dependencies
- Construction and behavior are decoupled

## Memory hook
- "Inject dependency, do not hardcode it."
