# Dynamic Programming

[Back to README](./README.md)

## DP - 1D / Kadane / Prefix

### 18. Max Score Jumps: `(destIdx - srcIdx) * value[dest]`

**Level / source tag:** Unspecified / Stones Jump variant

_Links:_ [Related LC 1696](https://leetcode.com/problems/jump-game-vi/)

**Problem.** From index `0` to `n-1`, you can jump from `j` to `i > j` and earn `(i-j) * a[i]`. Find maximum total score to reach the last index.

**Input / Output.**
- Input: `int[] a`
- Output: `long` max score

**Constraints.**
- `1 <= n <= 2e5`
- `|a[i]| <= 1e9`

**Examples.**
- `[3,12,9,10] -> 32`
- `[1,2,3,4,5] -> 20`
- Additional notes include `[5,4,3,2,1] -> 10` and `[3,5,2,8,1] -> 25`

**Approach direction.**
- DP: `dp[i] = max_j dp[j] + (i-j)*a[i] = i*a[i] + max_j(dp[j] - j*a[i])`.
- This is line-query style; consider Li Chao tree / convex hull trick depending on values.

### 25. Max Subarray Sum and Follow-up with Equal End Values

**Level / source tag:** Onsite Follow-up

_Links:_ not provided in pasted notes.

**Problem.**
1. Find maximum subarray sum.
2. Follow-up: find indices `[i,j]` achieving max sum with constraint `nums[i] == nums[j]`.

**Input / Output.**
- Input: `int[] nums`
- Output base: `int maxSum`
- Follow-up: `int[] ij`

**Approach.**
- Base: Kadane.
- Equal endpoints: use prefix sums. For each value `v`, keep the minimum prefix before an occurrence of `v`; when seeing `v` at `j`, maximize `prefix[j+1] - minPrefixForValue[v]`.

### 131. Bomb Pair Detonation to Maximize Sum

**Level / source tag:** Google Interview

_Links:_ not provided in pasted notes.

**Problem.** `bombs[i]` radius at index `i` removes interval `[i-r, i+r]`. Choose pairs `(i,j)` whose ranges do not interfere/overlap, maximize sum of radii.

**Example.**
- `bombs = [3,1,1,1,3] -> 6`

**Approach.**
- Interpret each bomb as weighted interval.
- Choose compatible intervals / pairs; likely weighted interval scheduling variant.

## DP - Subset / Knapsack

### 62. Partition Equal Subset Sum with Negatives

**Level / source tag:** Google variant

_Links:_ [LC 416 Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)

**Problem.** Given `nums`, which may include negative values, determine if the array can be partitioned into two subsets with equal sum.

**Follow-ups.**
- Return one such partition.
- Return all such partitions.

**Input / Output.**
- Input: `int[] nums`
- Output: boolean or variant lists.

**Approach.**
- Sum must be even.
- With negatives, offset DP over possible sums or use hash set of reachable sums.

### 65. Sum of Squares to X (Any List + Shortest List)

**Level / source tag:** Google SWE Early Career OA

_Links:_ not provided in pasted notes.

**Problem.** Given positive integer `x`, return any multiset/list whose squares sum to `x`. Follow-up: return the shortest list.

**Input / Output.**
- Input: `int x`
- Output: `List<Integer>`

**Approach.**
- Any list: greedy may not always be shortest, but Lagrange guarantees <=4 squares for positive integers.
- Shortest: DP over sums or BFS from `x` subtracting squares.

## DP - Sequence / LIS

### 43. LIS with Increasing Differences

**Level / source tag:** Onsite set

_Links:_ not provided in pasted notes.

**Problem.** Find a longest increasing subsequence where consecutive differences form a strictly increasing sequence.

**Input / Output.**
- Input: `int[] nums`
- Output: length.

**Approach.**
- DP on `(endingIndex, lastDiff)`.
- Coordinate-compress differences and query max previous length with smaller difference.
- Typical starting point: `O(n^2 log n)`.

### 93. Longest Subsequence / LIS with Difference = 1

**Level / source tag:** Onsite R1 / Coding R3

_Links:_ [Related LC 300 LIS](https://leetcode.com/problems/longest-increasing-subsequence/), [Related LC 673 Number of LIS](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)

**Problem.** Find longest subsequence where consecutive elements differ by exactly 1, or specifically increasing by 1 depending on variant.

**Example.**
- `[2,3,1,4,3,5,6] -> 5` via `[2,3,4,5,6]`

**Approach.**
- If increasing by exactly 1: `dp[x] = dp[x-1] + 1`.
- If absolute diff 1: `dp[x] = 1 + max(dp[x-1], dp[x+1])` while scanning.

### 123. Reconstruct Itinerary with Minimum Edit Cost

**Level / source tag:** Google Onsite Round 4

_Links:_ [LC 1548 Most Similar Path in a Graph](https://leetcode.com/problems/the-most-similar-path-in-a-graph/)

**Problem.** Country has cities with 3-letter names and roads. Given a remembered itinerary such as `AAA, BBB, CCC, DDD`, find the valid itinerary minimizing letter changes.

**Example from notes.**
- Remembered itinerary impossible because `BBB` not connected to `CCC`, and `DDD` does not exist.
- With two-letter change: `AAA, XBB, CCC, DDY`.
- Alternative `AAA, BBB, GOO, DDY` costs 4, worse.

**Approach.**
- DP over time step and city: cost to end at city at step `i` plus edit distance to remembered city.
- Transition only over roads.

## DP - Interval / Expression

### 2. Paint Fence with Horizontal/Vertical Strokes (Minimum Strokes)

**Level / source tag:** L3 R1

_Links:_ [Related LC 1526](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/)

**Problem.** You have `n` planks with heights `h[i]`. In one vertical stroke, paint an entire single plank. In one horizontal stroke, pick a segment `[l..r]` and reduce all heights in it by 1. Find the minimum strokes to paint all heights to zero.

**Input / Output.**
- Input: `int[] heights`
- Output: `int` minimum strokes

**Constraints.**
- `1 <= n <= 10^5`
- `0 <= heights[i] <= 10^9`

**Examples.**
- `[1,1,1] -> 1`
- `[3,1,3] -> 3`
- `[4,1,3,2] -> 4`

**Approach note from pasted list.**
- Divide and conquer by minimum height in a segment.
- Compare horizontal painting cost with all-vertical baseline.
- Closest known problem: Codeforces 448C - Painting Fence. LC 1526 is related but not the same operation model.

## DP - Bitmask / State Compression

### 77. OA: Maximize `F(B)` with OR and XOR

**Level / source tag:** Google OA 2024 SWE

_Links:_ not provided in pasted notes.

**Problem.** Given array `A` and integer `K`, choose subsequence `B` of length `2K` to maximize:

```text
F(B) = (OR of first K elements) XOR (OR of last K elements)
```

**Input / Output.**
- Input: `int[] A`, `int K`
- Output: max value.

**Approach.**
- DP over index, count chosen in first/second half, and OR masks when value bit-width is small.
- Or compute possible OR values for choosing K from prefix/suffix and combine split points.

## DP - Counting

### 13. Recover Coin Denominations from Ways DP

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Given `dp[0..T]`, where `dp[x]` is the number of ways to make sum `x` using an unknown positive unbounded coin set, reconstruct a possible coin set consistent with the DP array.

**Input / Output.**
- Input: `int[] dp` of length `T + 1`.
- Output: `List<Integer> coins`.

**Constraints.**
- `dp[0] = 1`.
- Pasted master says `dp[T] > 0`.

**Example.**
- `target = 10`
- `dp = [1,0,1,0,1,1,2,1,2,1,3]`
- Possible output: `coins = [2,5,6]`

**Approach note.**
- Process sums from small to large.
- Maintain reconstructed ways from coins found so far.
- If reconstructed ways at sum `x` is smaller than target `dp[x]`, coin `x` must exist; add it and update ways.

### 66. Palindromic Triples Count

**Level / source tag:** Google OA

_Links:_ not provided in pasted notes.

**Problem.** Given string `S`, count triples of palindromic substrings `(i1,j1),(i2,j2),(i3,j3)` with `i1 <= j1 < i2 <= j2 < i3 <= j3`.

**Input / Output.**
- Input: `String s`
- Output: `long` count.

**Approach.**
- Precompute palindrome table or Manacher-derived counts.
- Count pal substrings ending before each index and starting after each index, combine around middle palindrome.

### 74. Distinct Decimal Values of Subsequences of Binary String

**Level / source tag:** Google India OA Jul 2024

_Links:_ not provided in pasted notes.

**Problem.** Given binary string `s`, consider all non-empty subsequences, interpret as binary numbers, and count distinct decimal values modulo `1e9+7`.

**Input / Output.**
- Input: `String s`
- Output: `int` modulo.

**Approach.**
- Need account for leading zeros mapping to same decimal value.
- This is a custom counting DP; clarify constraints.

## DP - Game / Scheduling

### 39. Consultant Schedule Across Two Cities with Travel

**Level / source tag:** Onsite set

_Links:_ [Related LC 568 Maximum Vacation Days](https://leetcode.com/problems/maximum-vacation-days/)

**Problem.** Arrays `A[i]`, `B[i]`: earnings if working in city A or B on day `i`. Travel day earns 0. Choose schedule string of `A`, `B`, `T` maximizing total. Start in either city.

**Input / Output.**
- Input: `int[] A, int[] B`
- Output: schedule string.

**Approach.**
- DP by day and current city.
- Transition same city earns, switch city requires a travel day with 0 earning.
