# LSP Violated

## Problem
- `ElectricCar` implements `Car` but cannot honor `turnOnEngine()`.
- Caller expects all `Car` implementations to support the full contract.

## Why this is bad
- Substitution breaks at runtime.
- Base abstraction is lying about what all subtypes can do.

## Memory hook
- "If subtype throws 'unsupported', base contract is wrong."
