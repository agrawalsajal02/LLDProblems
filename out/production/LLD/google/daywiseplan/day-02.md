# Day 2: Focused 4-Question Set

**Date:** Wednesday, May 27, 2026

**Focus:** Core graph/heap/interval drill: multi-source BFS, shortest path thinking, sweep line, max-heap cooldown.

## Checklist

- [ ] Q6. 3D Lattice: Torch/Wire Power Propagation (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Multi-source BFS)
- [ ] Q32. Min-Cost Path (Grid/Graph) (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Shortest Path / Dijkstra)
- [ ] Q34. Active Users per Time `[0,N)` (Intervals, Sweep Line, Difference Array / Intervals - Timeline / Active Counts)
- [ ] Q4. ScoreQueue / Ads: Highest Score with No Consecutive Same Content (Heap, Priority Queue, Streaming / Heap - Cooldown / Scheduler)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q6. 3D Lattice: Torch/Wire Power Propagation

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Multi-source BFS

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** L3 Onsite

_Links:_ [LC 994 Rotting Oranges](https://leetcode.com/problems/rotting-oranges/), [LC 542 01 Matrix](https://leetcode.com/problems/01-matrix/)

**Problem.** Nodes in a 3D lattice are either torch (`value = 16`) or wire (`value = 0`). Power propagates from torches to adjacent wires, losing 1 per step. Multiple torches can feed a node. Compute final graph values after propagation stabilizes.

**Input / Output.**
- Input: graph representation: nodes, adjacency, initial values.
- Output: final values per node.

**Constraints.**
- Up to `N <= 10^5` nodes.
- Sparse adjacency.

**Example.**
- A chain `16 -> 0 -> 0` becomes `16 -> 15 -> 14`, unless another nearby torch gives a higher value.

**Approach note.**
- Multi-source BFS / priority BFS from all torches.
- Similar shape to rotting oranges / 01 matrix, but values decrease from sources.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q32. Min-Cost Path (Grid/Graph)

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Shortest Path / Dijkstra

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Onsite set

_Links:_ [LC 743 Network Delay Time](https://leetcode.com/problems/network-delay-time/), [LC 1631 Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/), [LC Google shortest path tag](https://leetcode.com/problemset/?companySlugs=google&page=1&topicSlugs=shortest-path)

**Problem.** Find a path with minimum cost in a grid or graph. The pasted list marks this as generic and says to clarify weights.

**Input / Output.**
- Input: weighted grid/graph.
- Output: min cost and optionally path.

**Approach.**
- Unweighted: BFS.
- Nonnegative weights: Dijkstra.
- 0/1 weights: 0-1 BFS.
- Grid effort / max-edge minimization: Dijkstra over state cost or binary search + BFS.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q34. Active Users per Time `[0,N)`

**Topic:** Intervals, Sweep Line, Difference Array

**Type:** Intervals - Timeline / Active Counts

**Original topic file:** [INTERVALS_SWEEP_LINE.md](../INTERVALS_SWEEP_LINE.md)

**Level / source tag:** Onsite set

_Links:_ [LC 1109 Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/), [LC 253 Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

**Problem.** Given sessions `[start,end]` inclusive and `N`, compute for every time `t in [0,N)` the number of active users.

**Input / Output.**
- Input: `int[][] sessions`, `int N`
- Output: `int[] counts` length `N`

**Example.**
- `[(0,3),(1,4)], N=7 -> counts: t0=1, t1=2, t2=2, t3=2, t4=1`

**Approach.**
- Difference array: `diff[start]++`, `diff[end + 1]--` if inside bounds.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q4. ScoreQueue / Ads: Highest Score with No Consecutive Same Content

**Topic:** Heap, Priority Queue, Streaming

**Type:** Heap - Cooldown / Scheduler

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

**Level / source tag:** L4 / Onsite

_Links:_ [LC 621 Task Scheduler](https://leetcode.com/problems/task-scheduler/), [LC 358 Rearrange String k Distance Apart](https://leetcode.com/problems/rearrange-string-k-distance-apart/), [Google L4 Bangalore 2026 post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Maintain elements `(content: String, score: int)`.

APIs:
- `addElement(content, score)`
- `getElement()`

`getElement()` returns highest-score content, but cannot return the same content consecutively (1-turn cooldown). After being returned, its score decrements by 1 and stays in the pool. If no elements exist, return empty string.

**Constraints.**
- Up to `10^5` operations.
- Pasted master says content is unique at insertion.

**Examples from notes.**
- `apple(5), banana(3), cherry(5) -> apple -> cherry -> apple -> cherry -> banana ...`

**Follow-up from 2026 L4 post.**
- Introduce a cooldown/gap before the same ad can appear again while keeping operations close to `O(1)`.

**Related patterns.**
- Greedy heap with temporary holdout.
- Task scheduler / k-distance cooldown.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
