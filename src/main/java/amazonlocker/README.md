# Amazon Locker LLD - Memory Guide

Locker



%06d vs %06f


🔹 %06d → integers ke liye (whole numbers)
* d = decimal integer
* 6 = total width = 6 characters
* 0 = left side pe zero padding
  👉 Example:

String.format("%06d", 123);




🔹 Control karna ho toh?

String.format("%06.2f", 123.45);

Output:

123.45

Yahan:
* .2 = 2 decimal places
* 6 = minimum width (padding apply hoga agar chhota hua toh)





Instant.now().plus(7, ChronoUnit.DAYS);


public boolean isExpired() {
return !Instant.now().isBefore(expiration);
}


This guide helps you remember **flow and logic**, not code.

## 1) Core Objects
- `Locker`: orchestrator (deposit, pickup, open expired).
- `Compartment`: physical slot with `size` + `occupied`.
- `AccessToken`: code + expiry + compartment reference.
- `Size`: SMALL, MEDIUM, LARGE.

## 2) Deposit Flow (driver)
1. Find an available compartment of exact size.
2. Open compartment.
3. Mark it occupied.
4. Generate access token (code + expiry = now + 7 days).
5. Store token in map by code.
6. Return code.

## 3) Pickup Flow (customer)
1. Validate token code (non-empty).
2. Look up token in map.
3. If expired -> error.
4. Open compartment.
5. Clear deposit: mark free + remove token.

## 4) Expired Tokens (staff)
- Scan all tokens.
- If expired, open those compartments so staff can remove packages.

## 5) Must-Remember Rules
- Exact size match only.
- Token expires in 7 days.
- Expired token stays in map until staff clears package.

## 6) Minimal API (rebuild from memory)
- `Locker.depositPackage(Size)` -> `String code`
- `Locker.pickup(String code)` -> void/error
- `Locker.openExpiredCompartments()` -> void
- `AccessToken.isExpired()`
- `Compartment.isOccupied(), markOccupied(), markFree(), open()`
