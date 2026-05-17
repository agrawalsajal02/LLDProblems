# Pub/Sub System LLD - Phase-Wise Guide

## Problem
Design an in-memory publisher-subscriber system where publishers write to topics and subscribers receive topic messages.

## Requirements Covered
- Multiple topics.
- Multiple publishers and subscribers.
- Subscribe and unsubscribe at runtime.
- Async delivery in advanced phases.
- Per-topic, per-subscriber ordering.
- At-most-once/fire-and-forget delivery.
- Bounded memory/backpressure handling.
- Metrics for delivered and dropped messages.

## Folder Layout
- `phase1`: simple synchronous fan-out.
- `phase2`: async fan-out with one bounded queue per subscriber.
- `phase3`: Kafka-style retained topic log with per-subscriber offsets, reset offset, and stats.
- `alternate`: pull-based implementation where subscribers explicitly poll messages.

## Recommended Interview Progression
1. Start with `phase1` to explain entities and baseline APIs.
2. Move to `phase2` when interviewer asks for non-blocking delivery and slow subscriber isolation.
3. Move to `phase3` when interviewer asks for offsets, replay/reset, bounded retention, observability, or Kafka-like behavior.
4. Mention `alternate` when interviewer asks for another design: push-based callbacks are not the only option; pull-based consumers can be simpler operationally.

## Final Design Choice
Use `phase3` for the strongest answer to the prompt you shared. It balances:
- Async dispatch.
- Ordered delivery per subscriber.
- At-most-once semantics.
- Retained in-memory log.
- Bounded memory.
- Operational stats.

## Run All
```bash
javac -d /tmp/pubsub-all src/main/java/pubsubsystem/phase1/*.java src/main/java/pubsubsystem/phase2/*.java src/main/java/pubsubsystem/phase3/*.java src/main/java/pubsubsystem/alternate/*.java
java -cp /tmp/pubsub-all pubsubsystem.phase1.Phase1Demo
java -cp /tmp/pubsub-all pubsubsystem.phase2.Phase2Demo
java -cp /tmp/pubsub-all pubsubsystem.phase3.PubSubDemo
java -cp /tmp/pubsub-all pubsubsystem.alternate.PullPubSubDemo
```
