# Google Sheet Implementation

## Intuition

Ye Excel API ka stronger version hai jisme dependencies explicitly track hoti hain.

Example:

- `C1 = A1 + B1`
- `D1 = C1 + 5`

Ab agar `A1` update hota hai, to `C1` and `D1` dirty mark hone chahiye. Fir jab `get()` ya `print()` call hoga, dirty cells recursively recompute honge.

Main idea:

- Har cell apni `dependencies` rakhta hai: wo kin cells ko read karta hai.
- Har cell apne `dependents` rakhta hai: kaunse cells is par depend karte hain.
- Update/reset ke baad dependents ko dirty mark karo.
- Actual computation lazy rakho: value tabhi recompute karo jab needed ho.

## Classes

- `GoogleSheetImplementation`: main API class.
- `Cell`: raw value, cached value, dependency sets, dirty flag store karta hai.

## Functions

- `set(String cellName, String value)`: cell set karta hai and dependency graph update karta hai. Time: O(D + X), where D = dependencies in new formula, X = downstream dependents marked dirty.
- `reset(String cellName)`: cell clear karta hai and dependents dirty mark karta hai. Time: O(old D + X).
- `get(String cellName)`: computed value return karta hai. Time: O(F) if dirty, O(1) if cached.
- `print()`: active cells ka raw and computed value print karta hai. Time: O(C + recomputation needed).
- `printAsString()`: same output string me return karta hai. Time: O(C + recomputation needed).
- `evaluateCell(...)`: recursive formula evaluation with cache. Time: O(F) if dirty, O(1) if cached.
- `markDirty(...)`: changed cell ke saare downstream dependents dirty mark karta hai. Time: O(X), where X = affected dependent cells.
- `parseDependencies(...)`: formula se cell references nikalta hai. Time: O(L), where L = formula length.
- `removeOldDependencies(...)`: old formula dependencies clean karta hai. Time: O(old D).

## Complexity

- `set`: O(number of dependencies in formula + affected dependents to mark dirty)
- `reset`: O(affected dependents to mark dirty)
- `get`: O(formula dependency chain), cached ho to O(1)
- `print`: O(active cells + recomputation needed)
