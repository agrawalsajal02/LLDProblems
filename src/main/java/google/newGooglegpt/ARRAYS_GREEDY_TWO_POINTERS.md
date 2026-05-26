# Arrays, Greedy, Sorting, Two Pointers

[Back to README](./README.md)

## Arrays - Greedy

### 26. Bank Serve Maximum Contiguously After Start / Max Transactions

**Level / source tag:** Onsite / L4

_Links:_ not provided in pasted notes.

**Problem variants from pasted notes.**

**Variant A: contiguous after choosing start.**
- Initial funds `X`.
- Transaction array `R[i]`.
- Choose a start index by skipping any prefix.
- Once started, process sequentially until a withdrawal exceeds funds.
- Maximize number served.

**Variant B: any order.**
- Positive transaction = withdrawal, negative = deposit.
- You can process transactions in any order, but once you hit a withdrawal greater than current balance you stop.
- Maximize number processed.

**Input / Output.**
- Input: `int X` or `int T`, `int[] tx`
- Output: max count.

**Notes.**
- Clarify signs before coding.
- Variant A is sliding-window/prefix-min style.
- Variant B is greedy: do deposits first; for withdrawals use min-heap / sort ascending by amount.

### 97. Theater Booking Duplicate Ticket Fix

**Level / source tag:** SE-3

_Links:_ not provided in pasted notes.

**Problem.** Each customer accidentally got 2 tickets. Check whether it is possible to cancel exactly 1 ticket per customer to fix the booking; otherwise return “No”.

**Approach.**
- Model as matching/assignment depending on exact constraints:
  - customers -> possible tickets to keep
  - seat uniqueness constraints

### 114. Count Visible People Twist

**Level / source tag:** 2026 L4 preliminary

_Links:_ [2026 visible people post](https://leetcode.com/discuss/post/7881477/google-l4-chances-by-anonymous_user-ik8e/)

**Problem.** Similar to Count Visible People, but visibility rule changes: if observer is taller, shorter people in between do not block the view.

**Example.**
- Array: `1, 10, 6, 7, 9, 2, 4, 5`
- For `5`, visible people are `4, 2, 9, 10`.
- In usual LeetCode version, `2` would not be visible; here it is.

**Notes.**
- Initial part can be solved in `O(n)` for one direction.
- Follow-up asks compute for entire array; pasted author struggled to find optimized solution.

**Approach direction.**
- Clarify exact visibility relation.
- May require monotonic structure plus range max/min queries depending on rule.

### 118. Group Array into Consecutive Increasing Groups of Size 5

**Level / source tag:** 2026 Phone Screening

_Links:_ [LC 846 Hand of Straights](https://leetcode.com/problems/hand-of-straights/), [2026 SDE3 experience](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

**Problem.** Given an array, check whether it can be divided into groups of size 5 such that each group contains consecutive increasing numbers. Output boolean.

**Notes.**
- Discussion focused on duplicates and ensuring all elements are used exactly once.

**Approach.**
- Sort and greedily consume counts from smallest value.
- Similar to hand of straights.

### 127. Greedy Offset Commit Order

**Level / source tag:** Google Phone Screen

_Links:_ not provided in pasted notes.

**Problem.** Given order of processed offsets, output commit order. When offset `X` can be committed (offset 0 or all offsets `< X` are already ready/committed), commit as far as possible; otherwise output `-1`.

**Example.**
- Input `[2,0,1] -> [-1,0,2]`

**Approach.**
- Boolean ready set.
- Maintain next expected commit offset.
- After marking offset processed, advance while ready.

### 128. Largest k-digit Number from Array (Preserve Order)

**Level / source tag:** Google Phone Screen

_Links:_ [Related LC 402 Remove K Digits](https://leetcode.com/problems/remove-k-digits/)

**Problem.** Pick `k` elements from array in order to maximize resulting concatenated number.

**Example.**
- `arr=[4,9,0,2]`, `k=2 -> 92`

**Approach.**
- Monotonic decreasing stack; remove up to `n-k` digits.

## Arrays - Sorting / Reordering

### 23. Neighborhood Reassignment / Reorganize Items in Sections

**Level / source tag:** L3 Phone

_Links:_ not provided in pasted notes.

**Problem.** Given list of neighborhoods/sections, each a list of house numbers/items. Redistribute across the same row sizes so each row is sorted ascending and has no duplicate inside the same row. Row capacities must be preserved.

**Input / Output.**
- Input: `List<List<Integer>> blocks`
- Output: `List<List<Integer>> result`

**Constraints.**
- Sum sizes <= `2e5` in pasted master.

**Notes.**
- Also appears as “Reorganize Items in Sections”: 2D array, each row is a section; reorganize items so each section is sorted and unique while preserving row/column structure.

**Approach.**
- Count frequencies globally.
- If any value frequency exceeds number of rows that can hold it, impossible.
- Distribute high-frequency values round-robin, then sort each row.

### 38. Sort Odds, Keep Evens in Place

**Level / source tag:** Onsite set

_Links:_ not provided in pasted notes.

**Problem.** Sort only the odd-valued elements of an array while keeping even numbers at their original indices.

**Input / Output.**
- Input: `int[] arr`
- Output: modified array.

**Approach.**
- Collect odd indices and values.
- Sort values.
- Write sorted odd values back into collected indices.

### 96. Valid Playing Card Set

**Level / source tag:** SWE2 Onsite

_Links:_ not provided in pasted notes.

**Problem.** Check if cards form:
1. Same rank, any suit, at least 3 cards.
2. Same suit, consecutive ranks, at least 3 cards.

**Input / Output.**
- Input: card list.
- Output: boolean.

**Approach.**
- Count ranks for same-rank set.
- Group by suit and sort ranks for consecutive run.

## Arrays - Two Pointers / Sliding Window

### 115. Rearrange Valid Strings to Front Using Prefix API

**Level / source tag:** 2026 Phone 1

_Links:_ [2026 Google L4 interview post](https://leetcode.com/discuss/post/7685567/google-interview-l4-by-anonymous_user-6s1j/)

**Problem.** Given array of strings and API `bool isValid(string s)`. The API has hidden constant string `P` and returns true if `P` is a prefix of `s`. Rearrange array so all valid strings appear at the beginning while minimizing in-memory movements. Relative order of valid strings need not be preserved.

**Example.**
- `P = "a"`
- `isValid("ad") -> true`
- `isValid("awe") -> true`
- `isValid("apple") -> true`
- `isValid("cat") -> false`
- `isValid("dog") -> false`

**Approach.**
- Partition array with two pointers like quicksort partition.
- Count API call cost as `O(L)` string prefix check if discussing implementation.

## Arrays - Prefix / Difference

### 49. Array to Zero with Range Queries (Subtract 1 from Any Subsequence)

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Given array `arr` and queries `[l,r]`. For each query, you may subtract 1 from any subsequence of indices inside `[l,r]`. After all queries, can all entries become zero?

**Input / Output.**
- Input: `int[] arr`, `int[][] queries`
- Output: `boolean`

**Example.**

```text
arr=[1,2,3]
queries=[[0,1],[1,2],[0,2],[1,2]]
Output=true
```

**Approach.**
- Since each query can reduce each covered index at most once, each index `i` needs coverage count at least `arr[i]`.
- Difference array over query ranges.

### 76. Array Differences with Duplicates and Order Preservation

**Level / source tag:** Google Phone Screen Jul 2024

_Links:_ not provided in pasted notes.

**Problem.** Given arrays possibly with duplicates, output:
1. Elements in `A` not consumed by `B`.
2. Elements in `B` not consumed by `A`.

Second variant: preserve original order of remaining elements.

**Input / Output.**
- Input: `int[] A, int[] B`
- Output: `int[][] diffs`

**Approach.**
- Multiset counts.
- For order-preserving variant, first count the opposite side, then scan original array and consume counts.

### 101. Range Sum Query - Mutable

**Level / source tag:** Prep list

_Links:_ [LC 307 Range Sum Query Mutable](https://leetcode.com/problems/range-sum-query-mutable/)

**Problem.** Support updates and range sum queries on an array.

**Approach.**
- Fenwick Tree or Segment Tree.

## Arrays - Binary Search

### 10. Find Incompatible Pair of Unit Tests Using `testRunner`

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** `testRunner(subset)` returns false if any incompatible pair inside the subset fails together; true otherwise. All single tests pass. Find at least one failing pair. Optimize when `testRunner` costs `O(n)` per run.

**Input / Output.**
- Input: `int n`, black-box `testRunner`.
- Output: one bad pair `(i, j)`.

**Constraints.**
- `n <= 10^5`
- `testRunner` is monotone in the sense that adding tests can preserve/introduce failure.

**Examples.**
- Multiple bad pairs may exist; return any one.

**Approach notes.**
- Group testing / delta debugging.
- First isolate one side that is needed for failure, then isolate partner.
- Be careful optimizing total runner cost when runner time scales with subset size.

### 45. Sparse Bit Array via Range Query (Find All Ones)

**Level / source tag:** Onsite L3

_Links:_ not provided in pasted notes.

**Problem.** You have a sparse bit array and an API `query(L,R)` returning whether any `1` exists in `[L,R]`. Find all positions of ones using minimal queries. Pasted notes mention constant extra space and no recursion for one variant.

**Input / Output.**
- Input: black-box range query.
- Output: all positions containing 1.

**Approach.**
- Binary search to locate the first 1 in a range.
- Then continue searching to the right.
- For divide-and-conquer variant, split ranges only if query says a 1 exists.

### 64. IP Range to Country / City Mapping

**Level / source tag:** Google Telephonic Screening Hyderabad L4

_Links:_ not provided in pasted notes.

**Problem.** Given disjoint/sorted IP ranges `[start,end] -> country/city`, return the associated country/city for a query IP, else unknown/not found.

**Input / Output.**
- Input: ranges list, query IPv4 string or integer IP.
- Output: country/city string.

**Examples.**
- `{1.1.0.1 - 1.1.0.10} -> IND`; query `1.1.0.5 -> IND`.
- `(10-20 -> "NY")`, query `15 -> "NY"`, query `30 -> not found`.

**Approach.**
- Convert IPv4 to 32-bit integer stored in `long`.
- Binary search by range start; verify query <= range end.

### 104. Find Median without Sorting in Constant Space and Linear Time

**Level / source tag:** Prep list

_Links:_ not provided in pasted notes.

**Problem.** Find median without sorting the full array, using constant extra space and linear expected time.

**Approach.**
- Quickselect.
- Return type should be float/double for even length.

### 113. Task Scheduling Equal Execution Time + Minimum CPUs

**Level / source tag:** 2026 SDE3 Onsite

_Links:_ [2026 SDE3 experience](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

**Problem.** Multiple tasks have equal execution time and a set of machines. First determine earliest time by which all tasks can complete. Second, given number of tasks and execution time, find the minimum CPUs required to achieve that earliest completion time.

**Approach.**
- Scheduling formula for identical machines if all tasks available at time 0.
- For constraints/arrival times, binary search CPUs or time and validate capacity.

### 122. Guaranteed Binary Search Numbers

**Level / source tag:** Google Onsite Round 3

_Links:_ not provided in pasted notes.

**Problem.** Given an unsorted array of distinct integers, return numbers that can be found by standard binary search on the array despite it not being sorted.

**Example from notes.**
- `[4, 3, 5, 8, 9, 6, 7] -> 3, 5, 8, 10` (example in pasted post appears to include `10`, likely typo/not in array; preserve and clarify in interview).

**Approach.**
- A value at index `i` is findable if it lies within the valid min/max bounds imposed by binary search path to `i`.

## Arrays - Simulation

### 1. Collatz Steps

**Level / source tag:** L4 Screening

_Links:_ not provided in pasted notes.

**Problem.** Given `n > 0`, repeatedly apply: if `n` is even, `n = n / 2`; otherwise `n = 3*n + 1`. Count the number of steps until `n == 1`.

**Input / Output.**
- Input: `int n` / `long n`
- Output: number of steps to reach 1

**Constraints.**
- `1 <= n <= 10^12`; use 64-bit.
- Worst-case steps are not known in a simple closed form.

**Examples.**
- `n = 1 -> 0`
- `n = 6 -> 8`, because `6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1`

**Notes from pasted experience.**
- L4 screening in notes.
- Follow-up: make it modular / OOP.
- Mapping: no exact LeetCode; simulation + optional memoization.

### 16. Valid Tournament Draw

**Level / source tag:** L5 Round 1

_Links:_ not provided in pasted notes.

**Problem.** Given initial bracket order `[1..N]`, where the best rank always wins, a draw is valid if in each round matches pair best-vs-worst, second-best-vs-second-worst, etc. Check validity across rounds.

**Input / Output.**
- Input: `int[] draw`, length is a power of 2.
- Output: `boolean`.

**Constraints.**
- `N <= 2^16`

**Example.**
- `[1,8,6,2,7,3,4,5] -> valid` per pasted note.

**Approach.**
- Simulate each round.
- In each block/round, expected rank pairs must sum to `N + 1` for remaining participants after relabeling by strength.

### 28. Odd/Even Jump Game with Value+1 Search and Updates

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Array `A`, start index `S`, update value `X`. Move 1 (odd): jump to the first left index with value `A[cur] + 1`. Move 2 (even): jump to the first right index with value `A[cur] + 1`. After each jump, set previous position `+= X` or to `X` depending on variant wording. Stop when no jump possible; detect infinite loop.

**Input / Output.**
- Input: `int[] A`, `int X`, `int S`
- Output: final index or `-1` if infinite.

**Example from notes.**
- `[2,1], X=2, S=1` is infinite.

**Approach.**
- Simulate with visited state `(index, parity, relevant values)` where mutation matters.
- Need a data structure from value to ordered indices for first-left / first-right.

### 70. Robot Sort with One Empty Slot

**Level / source tag:** Google Onsite 2022

_Links:_ not provided in pasted notes.

**Problem.** Array `a1..an,_` has one empty slot. Robot can move one box to the empty slot per operation. Sort ascending with `_` at either end. Minimize moves.

**Input / Output.**
- Input: array plus empty position.
- Output: sequence of moves or min count.

**Approach.**
- Model permutation cycles with a hole.
- Similar to sorting with swaps where every swap must involve empty slot.
