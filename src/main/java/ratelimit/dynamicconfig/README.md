# Dynamic Config Updates

This folder shows **both** common approaches to dynamic rate-limit config updates.

## Approach 1: Full rebuild + atomic swap
- throw away existing limiter instances
- create fresh limiters from new config
- atomically replace the endpoint map

### Pros
- simplest to implement
- easy to reason about
- handles algorithm change naturally

### Cons
- all per-key state is lost
- clients may get a fresh quota after reload

## Approach 2: In-place config update
- keep existing limiter instances
- update config inside the same limiter
- preserve per-key state when algorithm type stays same

### Pros
- keeps client history
- smoother rollout for config tuning

### Cons
- more complex
- each algorithm needs custom update logic
- if algorithm changes entirely, replacement is still needed

## Interview line
- `If simplicity matters, I would atomically rebuild limiter objects. If state continuity matters, I would support in-place updates for same-algorithm changes and replace only when algorithm type changes.`
