# Template Method

## Use when
- A workflow has fixed steps, but some individual steps vary.
- You want the parent class to define the skeleton and subclasses to customize parts.

## Do not use when
- The workflow itself changes often.
- Inheritance would be forced only for one tiny variation.
- Strategy would model the variation more cleanly.

## Memory hook
- "Parent defines the flow, child fills in the varying steps."

## Interview triggers
- Order processing
- Report generation
- File parsing
- Payment execution flow
