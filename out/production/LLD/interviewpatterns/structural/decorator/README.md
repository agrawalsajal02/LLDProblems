# Decorator

## Use when
- You want optional behavior layers at runtime.
- Features can be combined in different orders.

## Do not use when
- A fixed subclass hierarchy is enough.
- You only need one extra behavior once.

## Memory hook
- "Wrap object, delegate call, add behavior before or after."

## Interview triggers
- Logging wrapper
- Encryption/compression wrapper
- Caching wrapper
