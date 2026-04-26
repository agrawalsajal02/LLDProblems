# LSP Followed

## Design
- `Car` only exposes behavior common to all cars.
- `EnginePowered` is separate for engine-only vehicles.

## Why this is better
- Every implementation of `Car` remains safely substitutable.
- The abstraction matches reality.

## Memory hook
- "Keep only universal behavior in the shared contract."
