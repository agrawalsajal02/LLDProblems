# Graph, Grid, BFS, DFS, Shortest Path, DSU

[Back to README](./README.md)

## Graph - BFS Basics

### 27. Frog Min Steps with Variable Left/Right Bounds

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Frog starts at `start` and wants to reach `dest`. On each step choose to move left up to `l` or right up to `r` (pasted versions also mention parity-specific left/right moves). Find minimum steps.

**Input / Output.**
- Input: `int start, int dest, int l, int r`
- Output: min steps or `-1`.

**Variant note.**
- One pasted experience says odd moves allow `0..L` left and even moves allow `0..R` right. If parity matters, state includes `(position, parity)`.

**Approach.**
- BFS on bounded integer line or derive by gcd/reachability if jumps are unconstrained ranges.

### 63. Burgers Grid: Shortest Path Visiting All Burgers then End

**Level / source tag:** Google OA

_Links:_ not provided in pasted notes.

**Problem.** Grid with `S`, `E`, `B`, `O`. Must visit all `B` cells before reaching `E`. Find shortest 4-direction path length.

**Input / Output.**
- Input: `char[][] grid`
- Output: `int` shortest distance or `-1`.

**Example.**

```text
BOOB
OSOO
OOOE
BOOO
Answer = 11
```

**Approach.**
- BFS on `(r,c,maskOfBurgers)`.
- If burgers count is large, compute pairwise BFS distances and do TSP DP over burgers.

### 107. Top K Movies Similar to Given Movie

**Level / source tag:** 2026 L4 Round 1

_Links:_ [2026 Google L4 Bangalore post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Given movies with ratings and a similarity graph between movies, return top `K` movies similar to a given movie.

**Follow-up.**
- Optimize close to `O(log K)` time and `O(K)` space.

**Approach.**
- Traverse similarity graph from source to discover reachable similar movies.
- Maintain min-heap of size `K` by rating.
- For repeated queries, precomputation/caching may be discussed.

### 126. Transform Integer S to D using Add/Subtract/XOR

**Level / source tag:** Google Interview Aug 2024

_Links:_ not provided in pasted notes.

**Problem.** Given integer `S`, destination `D`, and array `A`, transform `S` to `D` using operations:
- add `A[i]`
- subtract `A[i]`
- xor `A[i]`

Each operation costs 1. Return minimum moves or `-1`.

**Example.**
- `A = [6,2,7,7]`, `S = 10`, `D = 21`

**Approach.**
- BFS over integer states with visited set.
- Need bounds/constraints to avoid infinite state space; clarify allowed range.

## Graph - Multi-source BFS

### 6. 3D Lattice: Torch/Wire Power Propagation

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

### 48. Highest Water Tower Cell Reaching Two Towns via Non-Increasing Paths

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

### 102. 01 Matrix

**Level / source tag:** Prep list

_Links:_ [LC 542 01 Matrix](https://leetcode.com/problems/01-matrix/)

**Problem.** Given binary matrix, compute distance from each cell to nearest zero.

**Approach.**
- Multi-source BFS from all zero cells.

## Graph - DFS / Flood Fill / Lakes

### 41. Robot Grid Color to Direction Lookup to Reach Star

**Level / source tag:** Onsite set

_Links:_ not provided in pasted notes.

**Problem.** Grid has colors `0..7` and a star `-1`. Build a mapping `color -> direction` so a robot starting at `(0,0)` reaches the star by following the direction assigned to the current tile color.

**Input / Output.**
- Input: `int[][] grid`
- Output: `Map<Integer, Character>` mapping to `U`, `D`, `L`, `R`.

**Approach direction.**
- Work backward from star and assign each color a direction that moves cells of that color closer to a reachable region.
- Need avoid contradictions when the same color appears in multiple places.

### 47. Coast Detection in Grid (Land vs Ocean vs Lake)

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

### 81. Graph Travel Max Weight within Time

**Level / source tag:** Unspecified

_Links:_ [LC 2065 Maximum Path Quality of a Graph](https://leetcode.com/problems/maximum-path-quality-of-a-graph/)

**Problem.** Weighted undirected graph, start at `A`, return to `A` within 24 time units, maximize sum of node weights collected once.

**Input / Output.**
- Input: graph, node weights, start, limit.
- Output: `{maxWeight, timeUsed}`.

**Approach.**
- DFS/backtracking with pruning if graph small.
- State includes current node, time used, collected set.
- This resembles “maximum path quality”.

### 109. Find All Lakes in a Grid, Including Land Inside Lake

**Level / source tag:** 2026 L4 Round 4

_Links:_ [2026 Google L4 Bangalore post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Find all lakes in a grid given one land cell. Initial version: water surrounded by land. Twist: what if there is land inside the lake?

**Approach.**
- Mark ocean-connected water from boundary.
- Remaining water components are lakes.
- If land islands exist inside lake, they do not connect lake to ocean; flood fill water only.

## Graph - Shortest Path / Dijkstra

### 32. Min-Cost Path (Grid/Graph)

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

### 53. Cat and Mouse Safest Path with Blockers

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

### 55. Shortest Path to Favorite Cities

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

### 78. Electricity Network over Roads Only (Connect All Cities)

**Level / source tag:** Google Mock L4

_Links:_ not provided in pasted notes.

**Problem.** Grid: `1 = road`, `0 = forbidden/farm`, `-1 = city`. Connect all cities through roads only with minimum build/cost. Output chosen road cells.

**Input / Output.**
- Input: `int[][] grid`
- Output: list of cells / grid.

**Approach.**
- If cost is number of road cells used to connect terminals, this is Steiner tree on grid, generally hard.
- For small number of cities, use bitmask DP over terminals + shortest paths.
- For interview variant, often pairwise shortest paths + MST approximation may be expected; clarify exact optimality.

## Graph - Topological Sort / DAG

### 84. Sequence Reconstruction

**Level / source tag:** Q89

_Links:_ [LC 444 Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/)

**Problem.** Given `nums`, a permutation of `[1..n]`, and subsequences `sequences`, check whether `nums` is the only shortest supersequence that includes all sequences.

**Constraints.**
- `1 <= n <= 10^4`
- `sequences[i].length >= 1`

**Examples.**
- `nums=[1,2,3]`, `sequences=[[1,2],[1,3]] -> false`
- `nums=[1,2,3]`, `sequences=[[1,2],[1,3],[1,2,3]] -> true`

**Approach.**
- Build graph edges from adjacent pairs in sequences.
- Kahn topological sort must be unique at every step and match `nums`.

### 91. Recipe Creation from Supplies and Dependencies

**Level / source tag:** Newly added

_Links:_ [LC 2115 Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/)

**Problem.** From recipes, ingredients, and initial supplies, return all recipes that can be produced.

**Approach.**
- Topological sorting from supplies.
- Ingredient -> recipes depending on it.

### 105. Validate Inequality Equations for Contradictions

**Level / source tag:** Prep list

_Links:_ not provided in pasted notes.

**Problem.** Given equations/relations like `a < b`, `b = c`, `d > c`, validate contradictions such as `c < a`.

**Approach.**
- Union equal variables.
- Build directed graph for `<` relations between components.
- Detect cycles with Kahn’s algorithm or DFS.

### 110. Course Schedule / Course Schedule II

**Level / source tag:** 2026 Round 1

_Links:_ [LC 207 Course Schedule](https://leetcode.com/problems/course-schedule/), [LC 210 Course Schedule II](https://leetcode.com/problems/course-schedule-ii/), [2026 Google L4 reject post](https://leetcode.com/discuss/post/8265899/google-l4-interview-reject-by-anonymous_-xsc3/)

**Problem.** Standard topological sort: determine whether all courses can be completed and/or return a valid ordering.

**Approach.**
- Kahn BFS indegree or DFS color cycle detection.

## Graph - Union-Find / Connectivity

### 15. Duplicate Groups by Any Shared Property

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

### 29. Circles Grouping (Overlap Connectivity)

**Level / source tag:** Onsite

_Links:_ [Related LC 547](https://leetcode.com/problems/number-of-provinces/)

**Problem.** Circles are given by centers and radii. A group is connected if circles overlap or touch, transitively. Determine whether all circles are in one group.

**Input / Output.**
- Input: `double[][] circles`
- Output: `boolean`

**Constraints.**
- Pasted master says `n <= 1e5`; naive all-pairs is too slow.

**Approach.**
- DSU with spatial indexing / sweep line / grid buckets for large n.
- Edge exists when distance between centers `<= r1 + r2`.

### 106. Friend Requests: Count Groups

**Level / source tag:** Prep list

_Links:_ not provided in pasted notes.

**Problem.** Given list of friend requests `(a,b)`, union friends. After all requests, determine number of individual groups.

**Approach.**
- DSU / Union-Find.

### 111. Sorted Array Diff Graph Connectivity Queries

**Level / source tag:** 2026 Screening

_Links:_ [2026 screening post](https://leetcode.com/discuss/post/7662886/google-screening-rounds-swe4-by-anonymou-5tzf/)

**Problem.** Given sorted array `arr` and integer `diff`, construct undirected graph where each node is an index. Connect `i` and `j` if `|arr[i] - arr[j]| <= diff`. Given queries `[u,v]`, return whether there is a path between `u` and `v`.

**Example.**
- `arr = [1,2,3,6]`, `diff = 2`, `queries = [[0,2],[1,3]]`
- Output: `[true,false]`

**Follow-ups.**
1. What if array is sorted descending?
2. What if input array is unsorted?
3. Which sorting algorithm will you use?

**Approach.**
- In sorted order, adjacent gaps `<= diff` define connected components; if a gap is greater than diff, connectivity breaks.
- For unsorted, sort value/index pairs and map original indices to component IDs.

## Graph - Bipartite Matching / Flow

### 7. Assign Questions to Volunteers by Tags (Maximum Matching)

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

### 17. Assign Apartments to People

**Level / source tag:** L3

_Links:_ not provided in pasted notes.

**Problem.** Apartments have `numRooms`. People either prefer roommates or not. Assign each person to one room, respecting capacities and maximizing satisfaction, or return any valid mapping if that is the requested variant.

**Input / Output.**
- Input: `List<Apartment{aptNumber,numRooms}>`, `List<Person{name,wantsHousemates}>`
- Output: `Map<Integer, List<String>> apartment -> people`

**Constraints.**
- `n <= 10^4`

**Approach.**
- Greedy can work for simple variants: solo people into 1-room apartments, roommate-preferring people into 2+.
- For maximize satisfaction with capacities: min-cost max-flow / bipartite matching with capacities.

## Graph - Dynamic Connectivity

### 44. Dynamic Friend Connectivity with Add/Remove: Earliest All Connected

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

## Graph - Tree as Graph / Probability

### 119. Grasshopper Probability in Tree / DAG at Infinity

**Level / source tag:** Google L3/L4 Interview Experience

_Links:_ not provided in pasted notes.

**Problem.** A grasshopper starts from root. If it is at a node with children, it must hop to a child; if at a leaf, it is stuck. Return probability map for each node at time infinity.

**Follow-up.**
- Same question in a directed acyclic graph.

**Approach.**
- Tree: DFS/BFS propagate probability equally among children; leaves accumulate final probability.
- DAG: topological order; distribute probability along outgoing edges; sink nodes retain probability.
