# Logging Service Extensibility

This package keeps follow-up interview problems separate from the base logging design.

## 1. Async / non-blocking logging
Base design:

```text
caller thread -> Destination.write() -> sink.write()
```

Problem:
- slow file or network I/O blocks caller

Extension:

```text
caller thread -> queue.put(record) -> return
worker thread -> queue.take() -> format -> sink.write()
```

Implemented in:
- `AsyncDestination`
- `OverflowPolicy`

Important concepts:
- one queue per destination
- one worker per destination
- bounded queue, never infinite memory
- single consumer means no per-destination sink lock is needed inside the async worker
- shutdown should drain queued records

Overflow policies in this code:
- `BLOCK_PRODUCER`: caller waits when queue is full
- `DROP_NEW`: caller does not block, newest record is dropped

Interview line:

`Async logging solves caller latency; destination locks solve write atomicity. They are different problems.`

## 2. Hierarchical named loggers
Real frameworks use names:

```text
com
com.app
com.app.payments
```

Child loggers inherit config from parents unless overridden.

Implemented in:
- `NamedLogger`
- `LoggerFactory`

Current behavior:
- `LoggerFactory` returns one logger per name.
- Parent is found using dotted prefix.
- `NamedLogger` can set its own min level.
- If no level is set, it inherits from parent.
- Log messages are prefixed with logger name.

Interview line:

`Factory owns the registry; logger owns name and parent pointer; effective config walks parent chain.`

## More natural extensions
### Remote sink
Add:

```text
class RemoteSink implements Sink
```

No change needed in `Logger`, `Destination`, or formatter classes.

### Log rotation
Add:

```text
class RotatingFileSink implements Sink
```

It can rotate by size or date internally.

### Config hot reload
Use a config object that can swap destination lists safely.
This is intentionally not in base v1 because it adds concurrency around config mutation.

### Context map / request id
Add fields to `LogRecord`, or add a `Map<String, String> context`.
Because `LogRecord` is one object, method signatures do not explode.
