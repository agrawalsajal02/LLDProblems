# Alternate Implementation - Pull-Based Pub/Sub

## Idea
Instead of pushing messages into subscriber callbacks, subscribers pull messages from the broker using a cursor.

## When To Use This Design
- Consumers want control over fetch rate.
- Broker should not own subscriber callback threads.
- Backpressure should be natural: slow consumers simply poll less often.
- The system looks more like Kafka consumers than SNS-style push callbacks.

## API
- `createTopic(topicName)`
- `publish(topicName, payload)`
- `subscribe(topicName, subscriberId)` returns `SubscriptionCursor`
- `poll(cursor, maxMessages)` returns messages and advances cursor

## Trade-Offs
- Consumer must poll repeatedly.
- No immediate callback delivery.
- Requires client-side loop and scheduling.
- Still needs retention handling for very slow consumers.

## Run
```bash
javac -d /tmp/pubsub-pull src/main/java/pubsubsystem/alternate/*.java
java -cp /tmp/pubsub-pull pubsubsystem.alternate.PullPubSubDemo
```
