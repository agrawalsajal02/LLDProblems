# Observer Extensibility

## Problem we are solving
- Basic observer notifies everyone every time.
- Real systems often need:
- only certain observers to be notified
- client-specific thresholds
- duplicate prevention after threshold is already crossed

## Example here
- Each client subscribes with its own threshold.
- A client is notified only when the price crosses from below-or-equal to above that threshold.
- If the price stays above the threshold, we do not keep sending repeated alerts.
- If the price drops back down and crosses above later, alert can fire again.

## Why this matters
- Prevents alert bombing.
- Gives per-client flexibility.
- Keeps observer useful in production-style alerting systems.

## Memory hook
- "Observer + subscription state = targeted alerts without spam."

## Interview trigger
- "How would you extend observer so users get only relevant notifications and no duplicates?"
