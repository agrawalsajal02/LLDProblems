# Extensibility Module

## What to extend next

### 1. New conditions
- `IN`
- `NOT`
- `OR`
- regex match
- date comparisons

### 2. New actions
- webhook
- Slack / email
- Jira ticket
- savings recommendation creation
- automated remediation

### 3. New cloud providers
- Add provider adapters that normalize raw billing data into `RuleContext`

### 4. Rule DSL / JSON-based definitions
- Current code builds rules in Java
- Next step: parse JSON/UI payload into condition trees and action configs

## Interview line
- "The core engine depends only on `RuleCondition` and `RuleAction`, so extending conditions, actions, or even cloud providers does not require changing engine internals."
