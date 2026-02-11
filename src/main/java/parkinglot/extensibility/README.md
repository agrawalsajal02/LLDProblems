# Parking Lot Extensibility (Interview Notes)

## 1) Multi-floor Garage
- Add `ParkingFloor` between `ParkingLot` and `ParkingSpot`.
- Lot scans floors to find available spot.

## 2) Per-type Pricing
- Use a map `VehicleType -> hourlyRate`.
- Compute fee based on ticket's vehicle type.
- Shown in `PerTypePricingParkingLot`.
