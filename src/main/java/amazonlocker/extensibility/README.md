# Amazon Locker Extensibility (Interview Notes)

## 1) Size Fallback
- Try exact size first.
- If not available, try larger sizes in order.
- For MEDIUM: MEDIUM -> LARGE, for SMALL: SMALL -> MEDIUM -> LARGE.

## 2) Maintenance / Out of Service
- Add `CompartmentStatus`.
- Skip `OUT_OF_SERVICE` compartments during allocation.

## 3) Two-Phase Deposit
- `reserveCompartment(size)` -> returns reservationId.
- Driver places package.
- `confirmDeposit(reservationId)` -> generates access token.
- Adds `RESERVED` state to compartments.

## Files
- `CompartmentStatus.java`
- `ExtCompartment.java`
- `Reservation.java`
- `ExtLocker.java`
- `README.md`
