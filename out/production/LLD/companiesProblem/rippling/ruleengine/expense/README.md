# Expense Rule Engine

## Intuition

Expense validation me rules alag-alag type ke ho sakte hain:

- Single expense rule: ek expense ka amount max limit cross na kare.
- Filtered rule: restaurant `vendor_type` ka amount limit cross na kare.
- Block rule: entertainment expense allowed nahi.
- Aggregate rule: trip total ya food total per trip limit cross na kare.

Isliye engine ko specific rule logic nahi pata hona chahiye. Engine bas rules ko call karega. Har rule khud decide karega ki kaunse expenses applicable hain.

Main idea:

- `Rule` interface banao.
- Input raw `List<Map<String, String>>` ho sakta hai, jaise problem statement me diya hai.
- Internally raw map ko typed `Expense` object me convert karo, taaki rule code clean rahe.
- `RuleEngine` sab expenses and trips ke liye `EvaluationReport` initialize kare.
- Har rule ko full expense list and report pass karo.
- Rule violations ko expense ya trip ke against add karo.

Is approach se future rules easy hain: new class banao, `Rule` implement karo, engine change nahi hota.

## Classes

- `ExpenseRuleEngineDemo`: runnable demo class.
- `Expense`: expense fields like expenseId, tripId, itemId, expenseType, amount, vendorType, vendorName.
- `EvaluationReport`: expense-level and trip-level violations alag-alag store karta hai.
- `RuleViolation`: failed rule ka name, targetId, message.
- `Rule`: common interface for all rules.
- `RuleEngine`: raw map input parse karta hai, rules run karta hai, and `EvaluationReport` return karta hai.
- `MaxSingleExpenseAmountRule`: single expense max amount check.
- `VendorTypeSingleExpenseLimitRule`: vendor type specific amount check.
- `BlockedFieldValueRule`: `expense_type`, `vendor_type`, ya `vendor_name` based ban rule.
- `BlockedExpenseTypeRule`: blocked expense type check.
- `TripTotalLimitRule`: trip aggregate total check.
- `ExpenseTypeTotalPerTripLimitRule`: trip ke andar expense type total check.

## Functions

- `RuleEngine.evaluateRules(...)`: raw `List<Map<String, String>>` parse karke all rules evaluate karta hai. Time: O(N + R * N) generally.
- `RuleEngine.evaluate(...)`: typed expenses par all rules evaluate karta hai. Time: O(R * N) generally.
- `Rule.evaluate(...)`: har rule ka common method. Time: depends on rule, simple rules O(N), aggregate rules O(N).
- `groupByTrip(...)`: aggregate rules ke liye expenses ko tripId se group karta hai. Time: O(N).
- `EvaluationReport.addExpenseViolation(...)`: expense-level violation add karta hai. Time: O(1) average.
- `EvaluationReport.addTripViolation(...)`: trip-level violation add karta hai. Time: O(1) average.
- `equalsIgnoreCase(...)`: safe string comparison helper. Time: O(L), where L = string length.

## Complexity

- Let `R` be number of rules and `N` be number of expenses.
- Simple rule: O(N)
- Aggregate rule: O(N)
- Total engine: roughly O(R * N)
