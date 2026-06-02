# Backtracking, Exact Cover, Search

[Back to README](./README.md)

## Backtracking - Tiling / Exact Cover

### 22. Tile Grid with Shapes (Rect, Square, L-shape) Exactly

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

### 79. Cover Holes with Horizontal/Vertical Planks (Minimum Planks)

**Level / source tag:** Recent Google Interview Dec 2024

_Links:_ not provided in pasted notes.

**Problem.** `m x n` grid with holes. Cover all holes using vertical or horizontal planks spanning contiguous holes. Minimize number of planks.

**Input / Output.**
- Input: grid of holes.
- Output: minimum planks.

**Approach.**
- Build bipartite graph:
  - Horizontal contiguous hole segments.
  - Vertical contiguous hole segments.
  - Each hole is an edge between its horizontal and vertical segment.
- Minimum planks = minimum vertex cover in bipartite graph = maximum matching (Konig).

## Backtracking - Expression Search

### 12. Expression to Target with Operators and Parentheses

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

### 20. Smallest Positive Not Formable with `+ - * / ( )`

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

## Backtracking - Combinatorics

### 72. Musical Notes Sequences to Sum 12 with Transitions

**Level / source tag:** Google Onsite

_Links:_ not provided in pasted notes.

**Problem.** Notes are in `{1,2,3}`. Valid transitions are given in a map. Find all sequences summing to 12 with valid transitions, including wrap from last note back to first.

**Input / Output.**
- Input: transitions map, target `12`.
- Output: `List<List<Integer>>`.

**Approach.**
- DFS/backtracking with remaining sum.
- When remaining sum is zero, validate last-to-first transition.

### 99. Max Score from Word Combinations with Overlap

**Level / source tag:** L5

_Links:_ not provided in pasted notes.

**Problem.** Given `words[]` with `score[]` and length limit `L`, words can overlap to form new words while accumulating scores. Maximize score under length limit.

**Approach.**
- This is search/DP over strings with overlaps, similar to shortest superstring with weights.
- Precompute overlap savings between word pairs.
- Use bitmask DP if number of words is small.

## Backtracking - Bitmask

### 37. Pick 5 Strings with All 25 Unique Letters

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
