# Phase 3 - Retained Log With Subscriber Offsets

## Goal
Build the interview-ready version: async delivery, topic log, per-subscriber offsets, offset reset, bounded retention, and stats.

## What It Supports
- Create and list topics.
- Publish immutable messages to a topic.
- Subscribe/unsubscribe dynamically.
- Deliver messages asynchronously.
- Maintain one offset per topic subscriber.
- Preserve order for each subscriber on a topic.
- Reset subscriber offset within retained range.
- Track delivered and dropped counts.

## Delivery Flow
1. Publisher calls `publish(topicName, message)`.
2. `PubSubBroker` resolves the `TopicHandler`.
3. `Topic` appends the message to an ordered log and assigns the next offset.
4. `Topic` signals waiting workers.
5. Each `SubscriberWorker` reads from its own offset.
6. Worker advances offset before callback, so callback failure does not cause retry.

## At-Most-Once Semantics
- No acknowledgments.
- No retries.
- Offset is claimed before delivery.
- Callback failures are logged and dropped.
- Messages removed by retention are counted as dropped for slow subscribers.

## Key Classes
- `PubSubBroker`: public API.
- `Topic`: bounded append-only in-memory log.
- `TopicHandler`: subscription lifecycle and worker lifecycle.
- `TopicSubscription`: per-subscriber offset and metrics.
- `SubscriberWorker`: async ordered delivery loop.
- `SubscriptionStats`: observability view.

## Trade-Offs
- In-memory only, so data is lost on process restart.
- One worker per subscription is simple and preserves order, but many subscribers can create many worker tasks.
- Reset offset is limited by retained log range.

## Extensions
- Partition each topic by message key.
- Add consumer groups.
- Persist topic log on disk.
- Add lag metrics: `topic.nextOffset - subscription.nextOffset`.
- Add configurable retention by time and size.

## Run
```bash
javac -d /tmp/pubsub-phase3 src/main/java/pubsubsystem/phase3/*.java
java -cp /tmp/pubsub-phase3 pubsubsystem.phase3.PubSubDemo
```
