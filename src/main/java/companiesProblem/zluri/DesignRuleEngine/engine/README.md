# Engine Module

## Responsibility
- Rule CRUD orchestration
- Rule ordering
- Rule evaluation
- Error handling

## Main idea
- Repository stores rules
- Engine fetches enabled rules for a given execution mode
- Engine orders rules by dependencies and priority
- Engine evaluates each rule against a context

## Interview line
- "The engine should know how to execute rules, but not how specific conditions or actions work internally."
