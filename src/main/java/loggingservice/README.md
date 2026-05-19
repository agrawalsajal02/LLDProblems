# Logging Service LLD

## Problem
Design an in-process logger library.

Application code should call methods like:

```java
logger.info("user signed in");
logger.error("payment failed");
```

The logger creates a log record and writes it to one or more configured destinations such as console and file.

## Functional requirements
- Support five ordered levels: `DEBUG < INFO < WARN < ERROR < FATAL`.
- Each log record has timestamp, level, message, and emitting thread name.
- One logger can write the same record to multiple destinations.
- Each destination has its own minimum level threshold.
- Format and output target vary independently.
- Support plain text and JSON formatting.
- Support console and file output.
- Concurrent calls should be safe: bytes of two records must not interleave on the same destination.

## Out of scope
- Distributed log aggregation.
- Network shipping in v1.
- Async or buffered logging in v1.
- Runtime hot-reload of config.
- Log rotation.
- Hierarchical named loggers in v1.

## Final approach
Use composition:

- `Logger` owns many `Destination`s.
- `Logger.log()` creates one immutable `LogRecord`.
- Each `Destination` filters by `minLevel`.
- Each `Destination` uses a `LogFormatter` to convert record to string.
- Each `Destination` uses a `Sink` to write the string somewhere.
- Each `Destination` has its own lock.

Memory line:

`Logger banata hai record -> Destination filter karta hai -> Formatter string banata hai -> Sink likhta hai`

## Core classes
- `LogLevel`: enum with severity order.
- `LogRecord`: immutable value object.
- `Logger`: public API and orchestrator.
- `Destination`: filter + format + lock + write workflow.
- `LogFormatter`: strategy for serialization.
- `PlainTextFormatter`: text formatter.
- `JsonFormatter`: JSON formatter.
- `Sink`: output target interface.
- `ConsoleSink`: writes to stdout.
- `FileSink`: appends to file.

## Why not one big Destination class?
Bad shape:

```text
Destination(type=CONSOLE | FILE, formatter, filePath)
if type == CONSOLE ...
else if type == FILE ...
```

Problem:
- every new target adds another `if`
- fields like `filePath`, `url`, `topic` become half-null
- violates Open/Closed Principle

Better:

```text
Destination + Sink
```

New target means new `Sink`, not changing `Destination`.

## Why formatter is separate from sink?
Format and target are independent dimensions.

Examples:
- plain text to console
- JSON to console
- plain text to file
- JSON to file

If we combine both, classes explode:

```text
PlainTextConsoleDestination
JsonConsoleDestination
PlainTextFileDestination
JsonFileDestination
```

Memory line:

`Do independent variation axes dikhe -> composition use karo`

## Thread safety intuition
Shared resource is the destination output target, not the whole logger.

So do not lock the entire `Logger.log()`.

Use one lock per destination:

```text
console destination lock
file destination lock
remote destination lock
```

This means a slow file write does not block console write.

Critical section:

```text
filter outside lock
format outside lock
sink.write inside lock
```

Why safe?
- `LogRecord` is immutable.
- formatters are stateless.
- only sink I/O needs protection.

## Core flow
### `Logger.log(level, message)`
1. Capture `Instant.now()`.
2. Capture `Thread.currentThread().getName()`.
3. Build `LogRecord`.
4. Send same record to each destination.

### `Destination.write(record)`
1. If `record.level < minLevel`, return.
2. Format the record.
3. Acquire destination lock.
4. Write to sink.
5. If sink fails, print diagnostic to stderr and do not crash caller.
6. Release lock.

## Design patterns
- Strategy: `LogFormatter`
- Strategy / Adapter: `Sink`
- Composition over inheritance: `Destination` composes formatter and sink
- Value Object: `LogRecord`
- Enum: `LogLevel`

## Interview checklist
Say these points clearly:

- This is in-process logging, not distributed log ingestion.
- Logger owns immutable destination list.
- Threshold belongs to destination, not logger.
- Format and sink are independent; separate interfaces avoid class explosion.
- Lock belongs to destination because that is where shared I/O resource lives.
- Logging failures should not crash business code.
- Async logging and hierarchical loggers are extensions, not v1.

## Example
```java
Destination console = new Destination(
        LogLevel.DEBUG,
        new PlainTextFormatter(),
        new ConsoleSink()
);

Destination file = new Destination(
        LogLevel.WARN,
        new JsonFormatter(),
        new FileSink("/tmp/app.log")
);

Logger logger = new Logger(List.of(console, file));

logger.info("only console");
logger.error("console and file");
```

## Complexity
- `Logger.log`: `O(D)` where `D` is number of destinations.
- Level filtering: `O(1)`.
- Formatting: depends on formatter, usually `O(message length)`.
- Sink write: depends on I/O.

## Extensibility package
See `loggingservice/extensibility`.

It contains examples for:
- async non-blocking destinations
- hierarchical named loggers
