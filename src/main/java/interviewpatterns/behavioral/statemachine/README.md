# State Machine

You call context that contain state
then you call state and pass context inside it , so that state can change the state inside the context

## Use when
- Valid actions depend on current state.
- You are getting many state-based conditionals.

## Do not use when
- State transitions are tiny and stable enough for one simple enum check.
- You only have one or two conditions with no real transition complexity.

## Memory hook
- "Context holds current state, state decides allowed transition."

## Interview triggers
- Order lifecycle
- Vending machine
- Document workflow
