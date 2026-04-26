# Facade

## Use when
- Subsystem is messy and caller wants one clean entry point.
- You want orchestration hidden behind a simple API.

## Do not use when
- There is no real subsystem complexity to hide.
- You are only forwarding a single call without simplification.

## Memory hook
- "Facade hides complexity, caller sees one simple method."

## Interview triggers
- Booking flow
- Checkout flow
- Game/controller orchestration
