# Parking Lot LLD - Memory Guide

Focus on remembering **flow** not code.

## 1) Core Objects
- `ParkingLot`: orchestrator (entry/exit, tickets, pricing)
- `ParkingSpot`: id + spotType (intrinsic)
- `Ticket`: id + spotId + vehicleType + entryTime
- `VehicleType`, `SpotType`: enums

## 2) Entry Flow
1. Find available spot for vehicle type.
2. Mark spot occupied (via occupiedSpotIds set).
3. Create ticket (id, spotId, vehicleType, entryTime).
4. Store ticket in active map.
5. Return ticket.

## 3) Exit Flow
1. Validate ticket id.
2. Lookup ticket.
3. Compute fee: round up hourly.
4. Free spot (remove from occupied set).
5. Remove ticket from active map.

## 4) Key Rules
- Reject entry if no compatible spot.
- Reject exit if ticket invalid/used.
- Hourly pricing, round up.

## 5) Minimal API
- `enter(vehicleType) -> Ticket`
- `exit(ticketId) -> fee`
