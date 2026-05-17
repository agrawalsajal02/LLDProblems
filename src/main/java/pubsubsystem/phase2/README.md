# Phase 2 - Async Fan-Out With Subscriber Workers

## Goal
Make publish non-blocking and prevent one slow subscriber from blocking other subscribers.

## What It Adds
- One `SubscriberWorker` per topic subscriber.
- Bounded queue per subscriber.
- Async delivery using an executor.
- Basic delivery/drop stats.
- Runtime unsubscribe.

## Delivery Semantics
- At-most-once/fire-and-forget.
- If subscriber queue is full, message is dropped for that subscriber.
- If subscriber callback throws, message is dropped.
- No retries and no acknowledgments.

## Ordering
Each subscriber has one worker consuming its queue sequentially, so messages for that subscriber remain ordered.

## Trade-Off
Messages are not retained in a topic log. Once enqueued or dropped, the broker cannot replay them.

## Run
```bash
javac -d /tmp/pubsub-phase2 src/main/java/pubsubsystem/phase2/*.java
java -cp /tmp/pubsub-phase2 pubsubsystem.phase2.Phase2Demo
```
