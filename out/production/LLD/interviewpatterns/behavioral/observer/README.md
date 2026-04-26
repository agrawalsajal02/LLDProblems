# Observer

## Use when
- One change should notify multiple dependent components.
- Producer should not know subscriber internals.

## Do not use when
- There is only one direct consumer.
- Synchronous notification fan-out would create too much coupling or latency and you really need queues/events instead.

## Memory hook
- "Subject changes, observers get notified."

## Interview triggers
- Stock ticker
- Order status listeners
- Inventory or alert subscriptions

## Extensibility
- See `extensibility/` for a more production-style observer example.
- It supports:
- client-specific thresholds
- notifying only relevant observers
- avoiding repeated alerts while value remains above threshold
