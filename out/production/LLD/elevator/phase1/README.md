# Elevator Phase 1

This phase focuses on the smallest useful version of the elevator problem.

## Goal

Understand how **one elevator** moves.

## What This Phase Implements

- one elevator
- fixed floor range
- request model
- `step()` based movement
- stop at requested floors
- reverse direction when no requests are ahead

## Main Classes

- `Elevator`
- `Request`
- `Direction`
- `RequestType`

## Intuition

Before solving multi-elevator scheduling, first make sure one elevator can:

1. accept requests
2. pick a direction
3. move one floor at a time
4. stop when required
5. reverse when needed

## Memory Trick

`Request lo -> direction choose karo -> stop ya move karo`

## Most Important Methods

- `addRequest(...)`
- `step()`
- `chooseInitialDirection()`
- `shouldStopAtCurrentFloor()`
- `hasAnyRequestAhead(...)`

## What To Read First

1. `Elevator`
2. `Request`
3. `Main`

## Why This Phase Matters

If phase 1 clear ho gaya, then elevator movement logic clear ho gaya.
Uske baad controller add karna much easier ho jata hai.
