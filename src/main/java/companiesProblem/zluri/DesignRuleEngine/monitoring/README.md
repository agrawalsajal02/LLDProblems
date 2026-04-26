# Monitoring Module

## Responsibility
- Audit rule evaluations
- Track rule performance
- Count matches and failures

## Why this matters
- Rule engines without observability become impossible to debug in production

## Implemented here
- audit log collector
- per-rule metrics collector

## Interview line
- "I treat rule evaluation like a production pipeline, so every execution is logged and measured."
