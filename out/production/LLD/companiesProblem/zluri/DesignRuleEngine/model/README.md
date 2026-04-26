# Model Module

## Responsibility
- Defines the core language of the rules engine
- Rule metadata
- Context data
- Execution results

## Why separate this?
- Other modules should depend on shared abstractions, not concrete engine details.

## Interview line
- "I keep model classes small and stable so conditions, actions, and execution logic can evolve independently."
