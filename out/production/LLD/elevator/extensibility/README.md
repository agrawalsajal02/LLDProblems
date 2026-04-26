# Elevator Extensibility (Interview Notes)

## 1) Express Elevator
- Only stops at a subset of floors (e.g., 0, 5, 9).
- Implemented by overriding `addRequest` to reject non-express floors.

## 2) Cancel Requests
- Add `removeRequest(floor, type)` to remove a queued stop.
- Demonstrated in `ExtElevator` which includes `removeRequest`.

Notes:
- Extensibility examples can be kept in separate classes to avoid changing the base design.
