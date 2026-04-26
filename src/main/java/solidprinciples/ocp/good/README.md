# OCP Followed

## Design
- `PaymentProcessor` depends on `PaymentMethod` abstraction.
- New payment type means a new class implementing `PaymentMethod`.

## Why this is better
- Existing processor code stays unchanged.
- Variants are added by extension, not by editing core logic.
- Testing stays localized to the new payment class.

## Example extension
- Add `WalletPayment implements PaymentMethod`
- No change needed in `PaymentProcessor`

## Memory hook
- "New type means add new class" -> OCP followed.
