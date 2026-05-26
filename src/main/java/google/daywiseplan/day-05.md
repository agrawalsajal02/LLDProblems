# Day 5: Focused 4-Question Set

**Date:** Saturday, May 30, 2026

**Focus:** Connectivity day: dynamic DSU, sparse range search, bipartite matching, free-time sweep.

## Checklist

- [ ] Q44. Dynamic Friend Connectivity with Add/Remove: Earliest All Connected (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Dynamic Connectivity)
- [ ] Q45. Sparse Bit Array via Range Query (Find All Ones) (Arrays, Greedy, Sorting, Two Pointers / Arrays - Binary Search)
- [ ] Q7. Assign Questions to Volunteers by Tags (Maximum Matching) (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Bipartite Matching / Flow)
- [ ] Q50. Common Free Time of All Persons (Intervals, Sweep Line, Difference Array / Intervals - Free Time)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q44. Dynamic Friend Connectivity with Add/Remove: Earliest All Connected

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Dynamic Connectivity

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Onsite set

_Links:_ [LC 1101 Earliest Moment When Everyone Become Friends](https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/)

**Problem.** Stream of logs has `addFriend(A,B)` and `removeFriend(A,B)` with timestamps. Find earliest timestamp at which all users become connected.

**Input / Output.**
- Input: events sorted by time.
- Output: earliest time or `-1`.

**Base.**
- With add-only events, this is LC 1101.

**Follow-up with removals.**
- Offline dynamic connectivity:
  - Convert active edge lifetimes into time intervals.
  - Segment tree over time.
  - DSU with rollback.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q45. Sparse Bit Array via Range Query (Find All Ones)

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Binary Search

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q7. Assign Questions to Volunteers by Tags (Maximum Matching)

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Bipartite Matching / Flow

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** L3 Onsite

_Links:_ [Related LC 1349](https://leetcode.com/problems/maximum-students-taking-exam/), [Related LC 1066](https://leetcode.com/problems/campus-bikes-ii/)

**Problem.** Each question has tags and each volunteer has tags. Assign at most one question to each volunteer and at most one volunteer to each question. A match is valid if they share any tag. Maximize number of assigned questions.

**Input / Output.**
- Input: list of `Question{id,tags}`, list of `Volunteer{id,tags}`.
- Output: matching pairs.

**Constraints.**
- Up to `10^4` total nodes in pasted master.

**Example.**
- Pasted note: example gives `A -> 4`, `B -> 2`, `C -> 3`, and question `1` unassigned.

**Approach.**
- Build bipartite graph by shared tags.
- Use Hopcroft-Karp for large cases.
- For capacity variants, use max flow.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q50. Common Free Time of All Persons

**Topic:** Intervals, Sweep Line, Difference Array

**Type:** Intervals - Free Time

**Original topic file:** [INTERVALS_SWEEP_LINE.md](../INTERVALS_SWEEP_LINE.md)

**Level / source tag:** Unspecified

_Links:_ [LC 759 Employee Free Time](https://leetcode.com/problems/employee-free-time/)

**Problem.** Given busy blocks `{personId,start,end}` and `totalTime`, return intervals where all persons are free.

**Input / Output.**
- Input: `List<Block> blocks`, `int totalTime`
- Output: `List<int[]> intervals`

**Approach.**
- Merge all busy intervals regardless of person.
- Complement inside `[0,totalTime]`.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
