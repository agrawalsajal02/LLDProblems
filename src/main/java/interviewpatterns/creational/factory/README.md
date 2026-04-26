# Factory

## Use when
- Caller should not know which concrete class to instantiate.
- Creation logic depends on input like `type`, `mode`, or `channel`.

## Do not use when
- There is only one concrete implementation.
- `new` in one place is already simple and clear.

## Memory hook
- "Factory chooses the object, caller just uses it."

## Interview triggers
- Notification type
- Payment method creation
- Parser/serializer creation
