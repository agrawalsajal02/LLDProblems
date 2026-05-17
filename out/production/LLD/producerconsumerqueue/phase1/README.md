# Phase 1 - Bounded Producer-Consumer Buffer

## Goal
Solve the foundational producer-consumer problem using `wait()` and `notifyAll()`.

## What It Supports
- Multiple producers.
- Multiple consumers.
- Fixed-capacity shared buffer.
- Producers block when buffer is full.
- Consumers block when buffer is empty.

## Why This Matters
This is the root of SQS, thread pools, job queues, async task executors, logger queues, and background worker frameworks.

## Key Classes
- `BoundedBlockingQueue`: synchronized bounded buffer.
- `Task`: unit of work.
- `Producer`: creates tasks.
- `Consumer`: consumes tasks.

## Limitations
- No visibility timeout.
- No ack/delete.
- No retry or dead-letter queue.
- Consumer gets the task and it is immediately removed.

## Run
```bash
javac -d /tmp/pcq-phase1 src/main/java/producerconsumerqueue/phase1/*.java
java -cp /tmp/pcq-phase1 producerconsumerqueue.phase1.Phase1Demo
```
