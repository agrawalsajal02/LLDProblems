# Day 10: Focused 4-Question Set

**Date:** Thursday, June 4, 2026

**Focus:** Backtracking/search: expression target, unformable positives, tiling, bitmask word pick.

## Checklist

- [ ] Q12. Expression to Target with Operators and Parentheses (Backtracking, Exact Cover, Search / Backtracking - Expression Search)
- [ ] Q20. Smallest Positive Not Formable with `+ - * / ( )` (Backtracking, Exact Cover, Search / Backtracking - Expression Search)
- [ ] Q22. Tile Grid with Shapes (Rect, Square, L-shape) Exactly (Backtracking, Exact Cover, Search / Backtracking - Tiling / Exact Cover)
- [ ] Q37. Pick 5 Strings with All 25 Unique Letters (Backtracking, Exact Cover, Search / Backtracking - Bitmask)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q12. Expression to Target with Operators and Parentheses

**Topic:** Backtracking, Exact Cover, Search

**Type:** Backtracking - Expression Search

**Original topic file:** [BACKTRACKING_EXACT_COVER.md](../BACKTRACKING_EXACT_COVER.md)

**Level / source tag:** L3 Phone

_Links:_ [LC 282 Expression Add Operators](https://leetcode.com/problems/expression-add-operators/), [LC 241 Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/)

**Problem.** Given digits or numbers and operator set `+ - * / ( )`, generate all expressions that evaluate to the target. Variant of LC 282; this version may include parentheses and division.

**Input / Output.**
- Input: `int[] nums`, `int target`
- Output: `List<String> expressions`

**Constraints.**
- `n <= 10`
- Watch overflow and division semantics.

**Example.**
- `nums = [2,3,4], target = 20 -> "(2+3)*4"`

**Approach notes.**
- LC 282 handles `+ - *` without explicit parentheses by tracking previous operand.
- Parentheses variant can use interval DP: compute all possible values/expressions for every subarray.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q20. Smallest Positive Not Formable with `+ - * / ( )`

**Topic:** Backtracking, Exact Cover, Search

**Type:** Backtracking - Expression Search

**Original topic file:** [BACKTRACKING_EXACT_COVER.md](../BACKTRACKING_EXACT_COVER.md)

**Level / source tag:** L3 Phone Screen

_Links:_ not provided in pasted notes.

**Problem.** Given distinct numbers, form expressions using any subset, reordering allowed, operators `+ - * /` and parentheses. Each number can be used at most once. Find the smallest positive integer not representable.

**Input / Output.**
- Input: `int[] nums`
- Output: `int` smallest positive not formable

**Constraints.**
- `n <= 8` typical.

**Example.**
- `[1,2] -> 4`

**Approach.**
- Enumerate subsets and all possible expression values using memoized interval/subset DP.
- Store rational values to avoid floating precision issues.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q22. Tile Grid with Shapes (Rect, Square, L-shape) Exactly

**Topic:** Backtracking, Exact Cover, Search

**Type:** Backtracking - Tiling / Exact Cover

**Original topic file:** [BACKTRACKING_EXACT_COVER.md](../BACKTRACKING_EXACT_COVER.md)

**Level / source tag:** SDE-3 Phone

_Links:_ [Related LC 1240](https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/)

**Problem.** Given an `m x n` grid with holes to fill and a multiset of shapes (rectangles, squares, L-shapes), decide whether they can tile the grid without gaps or overlaps. Rotations/flips are allowed.

**Input / Output.**
- Input: grid size, list of shapes.
- Output: `boolean`.

**Constraints.**
- Small backtracking instance in pasted master.

**Approach.**
- DFS with bitmask for small grids.
- Exact cover / Algorithm X (DLX) for cleaner modeling.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q37. Pick 5 Strings with All 25 Unique Letters

**Topic:** Backtracking, Exact Cover, Search

**Type:** Backtracking - Bitmask

**Original topic file:** [BACKTRACKING_EXACT_COVER.md](../BACKTRACKING_EXACT_COVER.md)

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Given list of strings of length 5, pick 5 strings whose 25 letters are all distinct.

**Input / Output.**
- Input: `List<String> words`
- Output: `List<String>` length 5 or empty.

**Approach.**
- Convert each word to bitmask; discard words with duplicate letters.
- Backtrack choose 5 compatible masks.
- Can model as clique in compatibility graph.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
