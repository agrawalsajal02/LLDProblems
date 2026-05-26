# Day 3: Focused 4-Question Set

**Date:** Thursday, May 28, 2026

**Focus:** DP + substring + DSU day: fence recursion, jump-score optimization, missing substrings, duplicate grouping.

## Checklist

- [ ] Q2. Paint Fence with Horizontal/Vertical Strokes (Minimum Strokes) (Dynamic Programming / DP - Interval / Expression)
- [ ] Q18. Max Score Jumps: `(destIdx - srcIdx) * value[dest]` (Dynamic Programming / DP - 1D / Kadane / Prefix)
- [ ] Q9. Shortest Missing Byte Sequence (Strings, Parsing, Grammar, Substrings / Strings - Subsequence / Substring)
- [ ] Q15. Duplicate Groups by Any Shared Property (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Union-Find / Connectivity)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q2. Paint Fence with Horizontal/Vertical Strokes (Minimum Strokes)

**Topic:** Dynamic Programming

**Type:** DP - Interval / Expression

**Original topic file:** [DP.md](../DP.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q18. Max Score Jumps: `(destIdx - srcIdx) * value[dest]`

**Topic:** Dynamic Programming

**Type:** DP - 1D / Kadane / Prefix

**Original topic file:** [DP.md](../DP.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q9. Shortest Missing Byte Sequence

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Subsequence / Substring

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** L3 Phone

_Links:_ [LC 1461 Check If a String Contains All Binary Codes](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/), [LC 1980 Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string/)

**Problem.** Given a byte stream limited initially to alphabet `{'a'..'f'}` and later generalized to bytes `0..255`, find the shortest string over the alphabet that does not occur as a substring.

**Input / Output.**
- Input: `String s` or byte array, alphabet size `Σ`.
- Output: any missing substring of minimum length.

**Constraints.**
- Pasted note mentions input up to `4GB` and memory considerations.

**Example.**
- `"abcdefacbeddefd" -> "aa"` because all single characters exist and `"aa"` is absent.

**Approach notes.**
- Test length `k = 1, 2, ...`.
- Mark observed substrings of length `k` via rolling hash or direct byte rolling index.
- Stop when not all `Σ^k` strings are present.
- For huge files, scan streaming and use bitsets when feasible.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q15. Duplicate Groups by Any Shared Property

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Union-Find / Connectivity

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** L3 Phone / SDE-II

_Links:_ [LC 721 Accounts Merge](https://leetcode.com/problems/accounts-merge/)

**Problem.** Each element has `id` and three properties. Two elements are in the same duplicate group if they share any property, transitively. Return all groups.

**Input / Output.**
- Input: `List<Element{id,p1,p2,p3}>`
- Output: `List<Set<String>> groups`

**Constraints.**
- `n <= 10^5`

**Examples from notes.**
- `E1: id1, p1, p2, p3`
- `E2: id2, p1, p4, p5`
- `E3: id3, p6, p7, p8`
- Output: `{{id1, id2}, {id3}}`

**Approach.**
- Union items that share any property.
- Hash map property value -> representative item.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
