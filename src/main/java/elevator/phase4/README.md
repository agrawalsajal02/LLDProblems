# Elevator Phase 4

This phase is the extensibility-oriented version of the elevator problem.

## Goal

Show how the design can evolve cleanly when the interviewer asks follow-up questions after the core version is already done.

## What This Phase Implements

- express elevator support
- priority floors: `0`, `5`, `9`
- request cancellation
- simple synchronized thread-safety
- express-aware selection strategy

## Main Classes

- `ElevatorController`
- `Elevator`
- `ElevatorSelectionStrategy`
- `ExpressAwareElevatorSelectionStrategy`
- `Request`

## Extensibility Covered

### 1. Express Elevator / Priority Floors

- one elevator is marked express
- express elevator only accepts express floors
- controller prefers express elevator for express-floor requests when it is idle

Memory line:

`special floors ke liye special lift`

### 2. Cancel / Undo Request

- `removeRequest(...)` in `Elevator`
- `cancelRequest(...)` in `ElevatorController`
- if request is still pending, it gets removed
- if already served, it simply returns `false`

Memory line:

`addRequest ka ulta removeRequest`

### 3. Concurrent Requests

This phase uses the simple lock-based explanation:

- `requestElevator(...)` is synchronized
- `requestDestination(...)` is synchronized
- `cancelRequest(...)` is synchronized
- `step()` is synchronized

This keeps `select + assign` atomic and avoids concurrent modification on shared state.

Memory line:

`simple concurrency ke liye single lock`

## Why Phase 4 Exists

Phase 1 to 3 solve the main elevator problem.
Phase 4 is for the follow-up round where interviewer asks:

- express elevator kaise add karoge?
- request cancel kaise hoga?
- concurrent hall calls kaise handle karoge?

## What To Read First

1. `ExpressAwareElevatorSelectionStrategy`
2. `Elevator`
3. `ElevatorController`
4. `Main`

## Interview Positioning

You can say:

1. Base movement logic is unchanged.
2. Express behavior is added by configuration plus smarter dispatch.
3. Request cancellation is a small inverse operation on the same request set.
4. Concurrency is handled with a simple synchronized approach first.
