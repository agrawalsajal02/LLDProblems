# Day 14: Focused 4-Question Set

**Date:** Monday, June 8, 2026

**Focus:** Final focused set before backlog: integer transform BFS, offset commit, largest k-number, car booking intervals.

## Checklist

- [ ] Q118. Group Array into Consecutive Increasing Groups of Size 5 (Arrays, Greedy, Sorting, Two Pointers / Arrays - Greedy)
- [ ] Q121. Furthest Building with Bricks and Ropes (Heap, Priority Queue, Streaming / Heap - Max/Min Selection)
- [ ] Q122. Guaranteed Binary Search Numbers (Arrays, Greedy, Sorting, Two Pointers / Arrays - Binary Search)
- [ ] Q123. Reconstruct Itinerary with Minimum Edit Cost (Dynamic Programming / DP - Sequence / LIS)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q118. Group Array into Consecutive Increasing Groups of Size 5

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Greedy

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

**Level / source tag:** 2026 Phone Screening

_Links:_ [LC 846 Hand of Straights](https://leetcode.com/problems/hand-of-straights/), [2026 SDE3 experience](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

**Problem.** Given an array, check whether it can be divided into groups of size 5 such that each group contains consecutive increasing numbers. Output boolean.

**Notes.**
- Discussion focused on duplicates and ensuring all elements are used exactly once.

**Approach.**
- Sort and greedily consume counts from smallest value.
- Similar to hand of straights.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q121. Furthest Building with Bricks and Ropes

**Topic:** Heap, Priority Queue, Streaming

**Type:** Heap - Max/Min Selection

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

**Level / source tag:** Google Onsite Round 2

_Links:_ [LC 1642 Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/)

**Problem.** Given building heights, start at first building with `b` bricks and `r` ropes. Moving to a higher next building requires either bricks equal to height difference or one rope. Find maximum distance reachable.

**Approach.**
- Use min-heap for climbs assigned to ropes; when ropes exceeded, pay smallest climb with bricks.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q122. Guaranteed Binary Search Numbers

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Binary Search

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

**Level / source tag:** Google Onsite Round 3

_Links:_ not provided in pasted notes.

**Problem.** Given an unsorted array of distinct integers, return numbers that can be found by standard binary search on the array despite it not being sorted.

**Example from notes.**
- `[4, 3, 5, 8, 9, 6, 7] -> 3, 5, 8, 10` (example in pasted post appears to include `10`, likely typo/not in array; preserve and clarify in interview).

**Approach.**
- A value at index `i` is findable if it lies within the valid min/max bounds imposed by binary search path to `i`.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q123. Reconstruct Itinerary with Minimum Edit Cost

**Topic:** Dynamic Programming

**Type:** DP - Sequence / LIS

**Original topic file:** [DP.md](../DP.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
