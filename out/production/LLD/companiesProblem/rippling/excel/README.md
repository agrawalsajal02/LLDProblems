# Excel Sheet API

## Intuition

MVP spreadsheet me hume cells ko string reference se access karna hai, jaise `A1`, `B2`.

Main idea:

- Sparse storage use karo: sirf wahi cells store karo jisme value hai.
- `Map<String, Cell>` se lookup/update/reset average O(1) ho jata hai.
- Cell raw value store karega, jaise `10`, `=9+10`, `=A1+B1`.
- Computed value print/get ke time recursively calculate karte hain.

Is version me dependency graph maintain nahi hota. Formula dynamic evaluate hota hai, isliye agar `A1` update hota hai, `=A1+10` print karte waqt latest value pick karega.

Cycle detection ke liye recursion ke time `visitingCells` set use hota hai.

## Classes

- `ExcelSheetApi`: main spreadsheet API.
- `Cell`: cell name and raw value store karta hai.

## Functions

- `set(String cellName, String value)`: cell me integer ya formula set karta hai. Empty string mile to reset. Time: O(1) average, excluding reset validation.
- `reset(String cellName)`: cell ko default empty value par le jata hai. Time: O(1) average.
- `getComputedValue(String cellName)`: cell ka computed integer value return karta hai. Time: O(F), where F = referenced formula chain size.
- `print()`: all non-empty cells ka raw and computed value print karta hai. Time: O(C + total formula evaluation cost).
- `printAsString()`: print output ko string form me return karta hai. Time: O(C + total formula evaluation cost).
- `evaluateCell(...)`: cell reference recursively evaluate karta hai. Time: O(F), with cycle detection.
- `evaluateRawValue(...)`: raw value integer hai ya formula, ye decide karta hai. Time: O(T + referenced cells), where T = formula tokens.
- `splitByPlus(...)`: formula ko `+` tokens me todta hai. Time: O(L), where L = formula length.

## Complexity

- `set`: O(1) average
- `reset`: O(1) average
- `getComputedValue`: O(number of referenced cells in formula chain)
- `print`: O(active cells + formula evaluation cost)
