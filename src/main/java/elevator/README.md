# Elevator LLD - Memory Guide

This guide focuses on **flow** so you can reconstruct code from logic.

## 1) Core Objects
- `ElevatorController`: accepts hall calls and steps the simulation.
- `Elevator`: moves, stores requests, decides stops.
- `Request`: (floor + type) to make direction-aware stops.
- `Direction`: UP, DOWN, IDLE.
- `RequestType`: PICKUP_UP, PICKUP_DOWN, DESTINATION.

## 2) Hall Call Flow
1. Validate floor and direction.
2. Pick best elevator (committed -> idle -> nearest).
3. Add `PICKUP_UP` or `PICKUP_DOWN` request to that elevator.

## 3) Destination Flow
- Inside elevator button just adds `DESTINATION` request.

## 4) Step() Movement (SCAN idea)
1. If no requests -> IDLE.
2. If IDLE and requests exist -> pick nearest request to set direction.
3. If at current floor and matching request -> stop, remove.
4. If no requests ahead -> reverse.
5. Move one floor.

## 5) Key Rules to Remember
- Stop only for matching pickup direction + any destination.
- Travel toward any requests even if you won’t stop yet.
- Requests are `floor + type` (not just floor).

## 6) Minimal API
- `ElevatorController.requestElevator(floor, direction)`
- `ElevatorController.requestDestination(elevatorIndex, floor)`
- `ElevatorController.step()`
- `Elevator.addRequest(floor, type)`
