# Day 13: Focused 4-Question Set

**Date:** Sunday, June 7, 2026

**Focus:** Recent + greedy: top-K similar movies, task scheduling, visible people twist, prefix API partition.

## Checklist

- [ ] Q107. Top K Movies Similar to Given Movie (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - BFS Basics)
- [ ] Q115. Rearrange Valid Strings to Front Using Prefix API (Arrays, Greedy, Sorting, Two Pointers / Arrays - Two Pointers / Sliding Window)
- [ ] Q119. Grasshopper Probability in Tree / DAG at Infinity (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Tree as Graph / Probability)
- [ ] Q120. Single-Threaded CPU and Two-Core Follow-up (Heap, Priority Queue, Streaming / Heap - Cooldown / Scheduler)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q107. Top K Movies Similar to Given Movie

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - BFS Basics

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** 2026 L4 Round 1

_Links:_ [2026 Google L4 Bangalore post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Given movies with ratings and a similarity graph between movies, return top `K` movies similar to a given movie.

**Follow-up.**
- Optimize close to `O(log K)` time and `O(K)` space.

**Approach.**
- Traverse similarity graph from source to discover reachable similar movies.
- Maintain min-heap of size `K` by rating.
- For repeated queries, precomputation/caching may be discussed.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q115. Rearrange Valid Strings to Front Using Prefix API

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Two Pointers / Sliding Window

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q119. Grasshopper Probability in Tree / DAG at Infinity

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Tree as Graph / Probability

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Google L3/L4 Interview Experience

_Links:_ not provided in pasted notes.

**Problem.** A grasshopper starts from root. If it is at a node with children, it must hop to a child; if at a leaf, it is stuck. Return probability map for each node at time infinity.

**Follow-up.**
- Same question in a directed acyclic graph.

**Approach.**
- Tree: DFS/BFS propagate probability equally among children; leaves accumulate final probability.
- DAG: topological order; distribute probability along outgoing edges; sink nodes retain probability.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q120. Single-Threaded CPU and Two-Core Follow-up

**Topic:** Heap, Priority Queue, Streaming

**Type:** Heap - Cooldown / Scheduler

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

**Level / source tag:** Google Onsite Round 1

_Links:_ [LC 1834 Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/)

**Problem.** Given tasks with `id`, `arrivalTime`, `executionTime`. At any time, from pending tasks pick the minimum execution time first on a single-core processor. Return order of execution.

**Follow-up.**
- Modify for two-core processor.

**Approach.**
- Sort tasks by arrival time.
- Min-heap by processing time then id.
- For multiple cores, maintain machine availability heap.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
