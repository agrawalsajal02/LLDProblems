# Day 6: Focused 4-Question Set

**Date:** Sunday, May 31, 2026

**Focus:** DP set: coin reconstruction, negative partition, city scheduling, LIS variants.

## Checklist

- [ ] Q13. Recover Coin Denominations from Ways DP (Dynamic Programming / DP - Counting)
- [ ] Q64. IP Range to Country / City Mapping (Arrays, Greedy, Sorting, Two Pointers / Arrays - Binary Search)
- [ ] Q39. Consultant Schedule Across Two Cities with Travel (Dynamic Programming / DP - Game / Scheduling)
- [ ] Q43. LIS with Increasing Differences (Dynamic Programming / DP - Sequence / LIS)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q13. Recover Coin Denominations from Ways DP

**Topic:** Dynamic Programming

**Type:** DP - Counting

**Original topic file:** [DP.md](../DP.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q64. IP Range to Country / City Mapping

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Binary Search

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q39. Consultant Schedule Across Two Cities with Travel

**Topic:** Dynamic Programming

**Type:** DP - Game / Scheduling

**Original topic file:** [DP.md](../DP.md)

**Level / source tag:** Onsite set

_Links:_ [Related LC 568 Maximum Vacation Days](https://leetcode.com/problems/maximum-vacation-days/)

**Problem.** Arrays `A[i]`, `B[i]`: earnings if working in city A or B on day `i`. Travel day earns 0. Choose schedule string of `A`, `B`, `T` maximizing total. Start in either city.

**Input / Output.**
- Input: `int[] A, int[] B`
- Output: schedule string.

**Approach.**
- DP by day and current city.
- Transition same city earns, switch city requires a travel day with 0 earning.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q43. LIS with Increasing Differences

**Topic:** Dynamic Programming

**Type:** DP - Sequence / LIS

**Original topic file:** [DP.md](../DP.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
