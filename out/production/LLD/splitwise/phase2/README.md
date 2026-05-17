# Splitwise Phase 2

This phase is the more interview-complete Splitwise version.

## Goal

Add group support, split validation, and a cleaner orchestration layer on top of the phase 1 core.

## Requirements Fulfilled

- users can be registered and looked up
- groups can be created and members can be added
- expenses can be attached to a group
- equal and unequal splits can be validated before balance update
- expense creation flow is moved into a dedicated service

## Main Classes

- `User`
- `UserController`
- `Group`
- `GroupController`
- `Expense`
- `Split`
- `ExpenseService`
- `BalanceSheetController`
- `SplitFactory`
- `EqualExpenseSplit`
- `UnequalExpenseSplit`

## Intuition

Phase 2 bolta hai:

- core balance logic same rahegi
- upar se group aur validation layer add hogi
- service orchestration karegi
- factory correct split validator choose karegi

## Flow To Remember

1. create users
2. create group
3. add members
4. choose split type
5. validate split
6. create expense
7. update balances

## Memory Line

`Group banao -> split validate karo -> expense add karo -> balances update karo`

## Best Files To Read

1. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/Main.java`
2. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/ExpenseService.java`
3. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/SplitFactory.java`
4. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/BalanceSheetController.java`
5. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase2/GroupController.java`

## Why This Phase Matters

Yahi woh version hai jo 1-hour LLD round me achhe se explain ki ja sakti hai:

- entities clear hain
- controller and service responsibilities clear hain
- split validation extensible hai
