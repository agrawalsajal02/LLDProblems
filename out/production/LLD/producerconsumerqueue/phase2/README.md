# Phase 2 - Worker Pool Task Queue

## Goal
Move from raw synchronization to a practical background worker framework.

## What It Supports
- Producers submit jobs.
- Multiple workers consume from one shared queue.
- Each job is processed by only one worker.
- Bounded queue provides backpressure.
- Basic submitted/rejected/pending stats.

## SQS Mapping
- `submit` is like `SendMessage`.
- Worker `poll` is like `ReceiveMessage`.
- Shared queue means competing consumers, not broadcast.

## Difference From SNS/PubSub
In SNS-style fan-out, every subscriber gets a copy. In this queue style, one job goes to one consumer.

## Limitations
- No visibility timeout.
- No delete/ack API.
- Handler failure drops the job.
- No retry or DLQ.

## Run
```bash
javac -d /tmp/pcq-phase2 src/main/java/producerconsumerqueue/phase2/*.java
java -cp /tmp/pcq-phase2 producerconsumerqueue.phase2.Phase2Demo
```
