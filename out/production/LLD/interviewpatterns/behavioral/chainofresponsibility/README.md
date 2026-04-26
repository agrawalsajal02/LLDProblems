# Chain Of Responsibility

## Use when
- A request can be handled by one of many handlers.
- You want to pass the request step by step until someone handles it.
- You want to remove long `if/else if/else` chains from one giant class.

## Do not use when
- Routing logic is tiny and stable.
- Handler order is unclear and would make debugging painful.
- Every handler always needs to run. That is often a pipeline, not classic chain of responsibility.

## Memory hook
- "Pass the request along the chain until one handler handles it."

## Interview triggers
- Support ticket escalation
- Logging levels
- Approval workflow
- Authentication / authorization checks
