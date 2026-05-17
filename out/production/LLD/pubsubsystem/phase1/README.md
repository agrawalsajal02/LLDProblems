# Phase 1 - Simple Synchronous Pub/Sub

## Goal
Build the smallest correct model: topics, subscribers, and ordered fan-out.

## What It Supports
- Create topics.
- Subscribe multiple subscribers to a topic.
- Publish messages to all active subscribers.
- Preserve message order because `publish` is synchronized.

## Trade-Off
- Publisher is blocked until all subscribers finish consuming.
- One slow subscriber slows everyone down.
- No unsubscribe, async delivery, retention policy, or metrics yet.

## Core Classes
- `PubSubBroker`: public API and topic registry.
- `Topic`: stores messages and subscribers.
- `Message`: immutable payload.
- `Subscriber`: callback interface.
- `PrintSubscriber`: demo subscriber.

## Run
```bash
javac -d /tmp/pubsub-phase1 src/main/java/pubsubsystem/phase1/*.java
java -cp /tmp/pubsub-phase1 pubsubsystem.phase1.Phase1Demo
```
