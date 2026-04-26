# Splitwise Phase 1

This is the smallest useful Splitwise design.

## Goal

Understand the core Splitwise flow without groups, controllers, or split-type abstractions.

## Requirements Fulfilled

- users should exist in the system
- one user can pay for a shared expense
- expense can be divided into per-user shares
- every user should know how much they owe or should get back

## Main Classes

- `User`
- `UserBalanceSheet`
- `Balance`
- `Split`
- `Expense`
- `ExpenseService`
- `BalanceSheetService`

## Intuition

Phase 1 ka seedha sa idea hai:

- ek payer hota hai
- split list batati hai kis user ka kitna share hai
- balance service payer aur borrowers dono ki side update karti hai

## Flow To Remember

1. create users
2. payer creates expense
3. pass split list
4. update balance sheet

## Memory Line

`Expense banao -> split do -> balance update karo`

## Best Files To Read

1. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/Main.java`
2. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/ExpenseService.java`
3. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/BalanceSheetService.java`
4. `/Users/sajalagrawal/Documents/LLD/src/main/java/splitwise/phase1/User.java`

## Why This Phase Matters

If phase 1 clear ho gaya, to Splitwise ka actual core clear ho gaya.
Baaki cheezein mostly structure, validation, aur scale ke liye add hoti hain.
