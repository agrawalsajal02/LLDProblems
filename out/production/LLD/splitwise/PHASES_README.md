# Splitwise In 2 Phases

This package is split into two phases so you can build the Splitwise LLD step by step in an interview.

Base package:

- `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise`

Phase folders:

- `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1`
- `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2`

## How To Remember The Problem

Remember Splitwise in this flow:

1. users exist
2. one user pays the full amount
3. expense is broken into splits
4. non-payers owe money
5. balance sheet gets updated
6. then groups and split validation can be added

Simple memory line:

`payer + splits + balance update = Splitwise core`

## Phase 1

Goal:

- solve the smallest meaningful Splitwise problem

Requirements fulfilled:

- create users
- one user pays for an expense
- split expense among users
- update who owes whom
- maintain per-user balance sheet

Why this phase exists:

- this is the actual heart of Splitwise
- if this phase is clear, the rest is mostly layering

## Phase 2

Goal:

- convert the basic solution into a cleaner interview-ready LLD

Requirements fulfilled:

- add groups
- add controller layer for user and group lookup
- add split-type based validation
- support equal and unequal split flows
- keep expense creation in a dedicated service

Why this phase exists:

- this is the version you can explain comfortably in a 1-hour LLD round

## Best Study Order

1. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/README.md`
2. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/Main.java`
3. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/ExpenseService.java`
4. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/BalanceSheetService.java`
5. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/README.md`
6. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/Main.java`
7. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/ExpenseService.java`
8. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/SplitFactory.java`
9. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/GroupController.java`

## Final Hinglish Summary

- phase 1: `expense aur balance update samjho`
- phase 2: `group, validation aur service layer add karo`
