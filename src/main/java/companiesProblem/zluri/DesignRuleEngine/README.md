# Design Rule Engine

## Problem
Build a rules engine for cloud cost optimization.

Example:
- "If monthly idle resource spend > 100, send a notification"
- "If department is Engineering and user_status is active and cost < 200, trigger optimization workflow"

## What the system should support
- Rule CRUD
- Enable / disable rules
- Real-time and scheduled execution
- Ordered execution when rules have dependencies
- Testing rules on historical data
- Extensible conditions and actions
- Logging, monitoring, and graceful error handling

## How to think about this in interviews

### Module 1: Rule model
- How a rule is represented
- Conditions
- Actions
- Status
- Priority
- Dependencies

### Module 2: Rule management
- Create / read / update / delete rules
- Enable / disable rules
- Validation

### Module 3: Rule execution
- Evaluate one rule
- Evaluate many rules
- Handle execution order
- Support scheduled and real-time triggers

### Module 4: Extensibility
- Add new conditions
- Add new actions
- Add new cloud metrics / adapters

### Module 5: Observability
- Audit logs
- Metrics
- Error reporting

### Module 6: Rule testing
- Replay historical inputs
- See which rules would have fired before deployment

## Interview answer structure
- Start with a `Rule` entity
- Separate `Condition` from `Action`
- Build an execution engine that evaluates rule conditions against a `RuleContext`
- Keep conditions and actions as interfaces so new ones can be plugged in
- Add repository + service for CRUD
- Add logging/metrics around execution

## Folder structure
- `model/`
- `conditions/`
- `actions/`
- `engine/`
- `monitoring/`
- `testing/`
- `extensibility/`

## Core design decision
- A rule is:
- metadata
- one root condition tree
- one or more actions

This keeps rule evaluation simple:
- if condition matches
- execute actions
- log results

## Example input
```json
{
  "department": "Engineering",
  "user_status": "active",
  "cost": 100
}
```

## Example rule
```text
IF department == "Engineering"
AND user_status == "active"
AND cost < 200
THEN send notification
```

## What is implemented in code
- In-memory rule repository
- Condition tree support
- Pluggable actions
- Ordered rule execution with dependencies + priority
- Audit logger
- Metrics collector
- Historical rule tester
- Demo main
