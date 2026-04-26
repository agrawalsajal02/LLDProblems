# OCP Violated

## Problem
- `PaymentProcessor` directly decides behavior using `if/else` on payment type.
- Every new payment mode means editing the same class again.

## Why this is bad
- Stable code keeps changing.
- New branches can break old ones.
- Testing scope keeps expanding in one place.

## Example failure mode
- Add `WALLET` payment
- Open `PaymentProcessor`
- Add one more `else if`
- Retest everything again

## Memory hook
- "New type means modify old class" -> OCP broken.
