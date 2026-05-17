# ATM

Source inspiration: `DesignATM` from the GitLab reference repo.

This version keeps ATM as a clean State Design Pattern example.

States:
- `IdleState`
- `HasCardState`
- `SelectOperationState`
- `CheckBalanceState`
- `CashWithdrawalState`

How to remember:
`Idle -> card -> pin -> operation -> action -> eject -> Idle`

Why State Pattern fits here:
- har ATM state me allowed actions alag hoti hain
- wrong action ko current state reject kar deta hai
- transition logic state classes me cleanly divide ho jata hai
