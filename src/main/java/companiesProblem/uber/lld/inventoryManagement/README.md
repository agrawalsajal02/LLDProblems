## Car Inventory And Reservation Management

This package models a simple in-memory car rental style system with two main responsibilities:

- `CarInventoryService` manages car onboarding, status updates, and availability search.
- `ReservationService` manages reservation creation, cancellation, completion, and price estimation.

### Main entities

- `Car`: inventory unit with category, city, hourly rate, and operational status.
- `CarAvailabilityCalendar`: per-car booking calendar used to detect slot conflicts.
- `Reservation`: confirmed/cancelled/completed booking for a user and a car.
- `ReservationRequest`: booking intent based on city, category, and time slot.

### Extensibility hooks

- `CarSelectionStrategy` lets us swap booking policies such as lowest-price, nearest-car, highest-rated, etc.
- `CarStatus` prevents cars under maintenance or retired from being returned in search results.

### Assumptions

- Inventory and reservations are stored in memory.
- A cancelled or completed reservation no longer blocks future searches.
- Pricing is estimated as `hourlyRate * durationInHours`.
