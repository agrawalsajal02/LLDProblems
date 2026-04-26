# Elevator Phase 2

This phase adds the controller and multiple elevators.

## Goal

Understand how requests are assigned when there are multiple elevators.

## What This Phase Implements

- multiple elevators
- central `ElevatorController`
- hall calls
- destination requests
- nearest-elevator selection
- controller tick calling `step()` on all elevators

## Main Classes

- `ElevatorController`
- `Elevator`
- `Request`

## Intuition

Now the problem becomes:

- user request kis elevator ko deni hai?

So the system is split into:

- `ElevatorController`
  - decides which elevator gets the request
- `Elevator`
  - decides how to move

## Memory Trick

`Controller choose karega, elevator move karegi`

## Most Important Methods

- `requestElevator(...)`
- `requestDestination(...)`
- `selectNearestElevator(...)`
- `step()`

## What To Read First

1. `ElevatorController`
2. `Elevator`
3. `Main`

## Why This Phase Matters

Phase 1 was movement.
Phase 2 is resource allocation.

Yahin se actual system-design feeling start hoti hai.
