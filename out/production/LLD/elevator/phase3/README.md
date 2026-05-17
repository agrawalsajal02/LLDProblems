# Elevator Phase 3

This is the final interview-oriented version.

## Goal

Make the design cleaner, more extensible, and easier to explain.

## What This Phase Implements

- strategy-based elevator selection
- smarter request-aware dispatch
- helper-method based movement logic
- clear separation of responsibilities

## Main Classes

- `ElevatorController`
- `Elevator`
- `ElevatorSelectionStrategy`
- `SmartNearestElevatorSelectionStrategy`
- `Request`

## Intuition

Phase 2 worked, but dispatch logic was directly inside controller.

In phase 3:

- controller orchestrates
- elevator moves
- strategy selects elevator

This makes the solution easier to extend later.

## Memory Trick

`Controller coordinate karega, strategy choose karegi, elevator chalegi`

## Most Important Methods

- `requestElevator(...)`
- `select(...)`
- `hasRequestsAtOrBeyond(...)`
- `step()`
- `shouldStopAtCurrentFloor()`

## Why Strategy Helps

If interviewer asks:

- nearest strategy
- committed-direction strategy
- express elevator strategy

then only strategy class changes, controller stays clean.

## What To Read First

1. `ElevatorSelectionStrategy`
2. `SmartNearestElevatorSelectionStrategy`
3. `Elevator`
4. `ElevatorController`
5. `Main`

## Why This Phase Matters

Yahi woh version hai jo interview mein confidently explain kiya ja sakta hai:

- modular
- readable
- extensible
- helper methods ke saath easy to remember
