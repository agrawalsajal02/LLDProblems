# Day 4: Focused 4-Question Set

**Date:** Friday, May 29, 2026

**Focus:** Grid graph day: ocean/lake, water-flow reverse BFS, safest path, favorite-city Dijkstra.

## Checklist

- [ ] Q47. Coast Detection in Grid (Land vs Ocean vs Lake) (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - DFS / Flood Fill / Lakes)
- [ ] Q48. Highest Water Tower Cell Reaching Two Towns via Non-Increasing Paths (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Multi-source BFS)
- [ ] Q53. Cat and Mouse Safest Path with Blockers (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Shortest Path / Dijkstra)
- [ ] Q55. Shortest Path to Favorite Cities (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Shortest Path / Dijkstra)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q47. Coast Detection in Grid (Land vs Ocean vs Lake)

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - DFS / Flood Fill / Lakes

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Onsite set

_Links:_ [LC 200 Number of Islands](https://leetcode.com/problems/number-of-islands/), [LC 463 Island Perimeter](https://leetcode.com/problems/island-perimeter/)

**Problem.** Grid with `'X'` land and `'.'` water. A `'.'` is ocean if connected to boundary water (unbounded on at least one side). Bounded water is a lake. A coast is any land directly adjacent to ocean water. Given `(r,c)`, return whether it is a coast.

**Input / Output.**
- Input: `char[][] grid`, `int r`, `int c`
- Output: `boolean`

**Example from notes.**

```text
x x x x x x
. . . x . x . .
. . . x x . x .
```

`isCoast(1,3) -> true`

**Approach.**
- Flood-fill boundary-connected water as ocean.
- A land cell is coast if any 4-neighbor is marked ocean.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q48. Highest Water Tower Cell Reaching Two Towns via Non-Increasing Paths

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Multi-source BFS

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Newly added

_Links:_ [LC 417 Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

**Problem.** Given `heights` grid and two town coordinates, find the highest elevation cell from which water can flow via non-increasing 4-direction paths to both towns. Return coordinates.

**Input / Output.**
- Input: `int[][] heights`, `int[] town1`, `int[] town2`
- Output: `int[] bestCell` or `{-1,-1}`.

**Example.**

```text
heights =
[ [4,9,7,6,5],
  [2,6,5,4,3],
  [6,5,1,2,8],
  [3,4,7,2,5] ]

town1=[1,4], town2=[3,1] -> [0,1] (height 9)
```

**Approach.**
- Reverse the flow.
- From each town, BFS/DFS to cells that can flow down to it; reverse move condition allows moving to equal/higher cells.
- Intersect reachable sets; choose max height.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q53. Cat and Mouse Safest Path with Blockers

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Shortest Path / Dijkstra

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Aug 2024 set

_Links:_ [LC 2812 Find the Safest Path in a Grid](https://leetcode.com/problems/find-the-safest-path-in-a-grid/)

**Problem.** Grid has mouse, cat, food, and cells with water (`1`). Mouse cannot step on water; cat can. Find maximum safeness path / minimal maximum risk so mouse reaches food while avoiding cat proximity.

**Input / Output.**
- Input: `int[][] grid`, `int[] mouse`, `int[] cat`, `int[] food`
- Output: safeness or distance depending on exact spec.

**Notes.**
- Pasted note says candidate gave PriorityQueue + Binary Search `O(N^2 log N)`; interviewer wanted `O(N^2)`.

**Approach.**
- Multi-source BFS from cat to compute cat distance/risk.
- Then solve widest path for mouse. For `O(N^2)`, use bucketed BFS by distance levels or process cells in descending safeness with DSU.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q55. Shortest Path to Favorite Cities

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Shortest Path / Dijkstra

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Google Onsite Round 2 Aug 2024

_Links:_ [Google shortest path tag](https://leetcode.com/problemset/?companySlugs=google&page=1&topicSlugs=shortest-path)

**Problem.** Weighted city graph. From city `A`, given favorite cities `[F1..Fn]`, determine which favorite city is reached fastest and the time.

**Input / Output.**
- Input: graph, source `A`, favorite set.
- Output: favorite city and time.

**Approach.**
- Dijkstra from `A`.
- Stop early when the popped node is a favorite if all edge weights are nonnegative.

**Complexity discussion from notes.**
- `(V + E) log V` with adjacency list and priority queue.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
