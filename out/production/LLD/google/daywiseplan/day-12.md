# Day 12: Focused 4-Question Set

**Date:** Saturday, June 6, 2026

**Focus:** Recent Google patterns: LFU variant, lakes with islands, course topo, sorted-diff connectivity.

## Checklist

- [ ] Q101. Range Sum Query - Mutable (Arrays, Greedy, Sorting, Two Pointers / Arrays - Prefix / Difference)
- [ ] Q108. LFU-like Cache with Even-Score Eviction (Design, Data Structures, LLD / Design - Cache / Queue)
- [ ] Q109. Find All Lakes in a Grid, Including Land Inside Lake (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - DFS / Flood Fill / Lakes)
- [ ] Q116. URL Shortener Unique String Generator with Frequency Threshold (Design, Data Structures, LLD / Design - Iterator / Generator)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q101. Range Sum Query - Mutable

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Prefix / Difference

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

**Level / source tag:** Prep list

_Links:_ [LC 307 Range Sum Query Mutable](https://leetcode.com/problems/range-sum-query-mutable/)

**Problem.** Support updates and range sum queries on an array.

**Approach.**
- Fenwick Tree or Segment Tree.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q108. LFU-like Cache with Even-Score Eviction

**Topic:** Design, Data Structures, LLD

**Type:** Design - Cache / Queue

**Original topic file:** [DESIGN_DATA_STRUCTURES_LLD.md](../DESIGN_DATA_STRUCTURES_LLD.md)

**Level / source tag:** 2026 L4 Onsite

_Links:_ [2026 Google L4 reject post](https://leetcode.com/discuss/post/8265899/google-l4-interview-reject-by-anonymous_-xsc3/)

**Problem.** Similar to LFU cache, but key-value values include `[Content: String, Score: int]`. On access, score increases by 1. Eviction follows standard cache priority but may evict only values whose score is even.

**Approach.**
- Clarify exact “standard pattern” priority: LFU frequency, LRU tiebreak?
- Maintain frequency buckets plus an index/set of even-score candidates.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q109. Find All Lakes in a Grid, Including Land Inside Lake

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - DFS / Flood Fill / Lakes

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** 2026 L4 Round 4

_Links:_ [2026 Google L4 Bangalore post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Find all lakes in a grid given one land cell. Initial version: water surrounded by land. Twist: what if there is land inside the lake?

**Approach.**
- Mark ocean-connected water from boundary.
- Remaining water components are lakes.
- If land islands exist inside lake, they do not connect lake to ocean; flood fill water only.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q116. URL Shortener Unique String Generator with Frequency Threshold

**Topic:** Design, Data Structures, LLD

**Type:** Design - Iterator / Generator

**Original topic file:** [DESIGN_DATA_STRUCTURES_LLD.md](../DESIGN_DATA_STRUCTURES_LLD.md)

**Level / source tag:** 2026 Onsite 1

_Links:_ [2026 Google L4 interview post](https://leetcode.com/discuss/post/7685567/google-interview-l4-by-anonymous_user-6s1j/)

**Problem.** Design a class generating unique lowercase strings for URL shortening.

Rules:
1. Strings use only `a-z`.
2. Threshold `T` is provided.
3. Frequency of any character in a generated string must be `<= T`.
4. Every API call returns a unique string.
5. Optimize for shorter strings first: all valid length-1 before length-2, etc.

**API.**

```cpp
class URLShortener {
public:
    URLShortener(int threshold);
    string getNext();
};
```

**Example.**
- Threshold `2`: length 1 outputs `a,b,c,...,z`; length 2 then `aa,ab,...,zz`; invalid strings like `aaa` at length 3 are skipped.

**Approach.**
- Enumerate strings like base-26 counter by length.
- Skip invalid candidates whose char frequency exceeds `T`.
- This guarantees uniqueness and shorter-first order.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
