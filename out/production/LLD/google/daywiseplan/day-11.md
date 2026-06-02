# Day 11: Focused 4-Question Set

**Date:** Friday, June 5, 2026

**Focus:** Math/geometry/bit: equal-area line, circle grouping, bit image flip, max rectangle points.

## Checklist

- [ ] Q52. Equal-Area Vertical Line Across Overlapping Rectangles (Math, Geometry, Bit Manipulation / Geometry - Rectangles / Lines / Circles)
- [ ] Q29. Circles Grouping (Overlap Connectivity) (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Union-Find / Connectivity)
- [ ] Q51. Flip 1-bit-per-pixel Image Horizontally In-Place (Math, Geometry, Bit Manipulation / Bit Manipulation)
- [ ] Q110. Course Schedule / Course Schedule II (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Topological Sort / DAG)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q52. Equal-Area Vertical Line Across Overlapping Rectangles

**Topic:** Math, Geometry, Bit Manipulation

**Type:** Geometry - Rectangles / Lines / Circles

**Original topic file:** [MATH_GEOMETRY_BIT_MANIPULATION.md](../MATH_GEOMETRY_BIT_MANIPULATION.md)

**Level / source tag:** Screening / Onsite

_Links:_ [Screening round discussion](https://leetcode.com/discuss/interview-question/5587195/Google-interview-experience-or-Screening-round)

**Problem.** Given rectangles `[x1,y1,x2,y2]`, possibly overlapping, find vertical line `x = const` that divides total rectangle area into equal left/right area. If line passes through a rectangle, split its area proportionally.

**Input / Output.**
- Input: `int[][] rects`
- Output: `double x`

**Notes.**
- Pasted screening note says use binary search on `x`.
- Be careful whether overlapping rectangle area is counted multiple times or union area. Pasted master says “overlapping rectangles” but binary search over sum of per-rectangle area usually counts overlap multiple times. Clarify in interview.

**Complexity.**
- `O(N log X)` for binary search with summed area.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q29. Circles Grouping (Overlap Connectivity)

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Union-Find / Connectivity

**Original topic file:** [GRAPH.md](../GRAPH.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q51. Flip 1-bit-per-pixel Image Horizontally In-Place

**Topic:** Math, Geometry, Bit Manipulation

**Type:** Bit Manipulation

**Original topic file:** [MATH_GEOMETRY_BIT_MANIPULATION.md](../MATH_GEOMETRY_BIT_MANIPULATION.md)

**Level / source tag:** Aug 2024 set

_Links:_ [Related LC 832](https://leetcode.com/problems/flipping-an-image/)

**Problem.** Image is stored as `byte[]`; width `w` bits (multiple of 8), height `h`. Each bit is one pixel. Flip horizontally in place by reversing bits per row.

**Input / Output.**
- Input: `byte[] image`, `int wBits`, `int h`
- Output: mutates input.

**Example.**
- Row `10100011 00001111` -> flipped row `11110000 11000101`

**Constraints from notes.**
- `8 <= w <= 10^4`
- `1 <= h <= 10^3`

**Approach.**
- Reverse byte order within row and reverse bits inside each byte using lookup table.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q110. Course Schedule / Course Schedule II

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Topological Sort / DAG

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** 2026 Round 1

_Links:_ [LC 207 Course Schedule](https://leetcode.com/problems/course-schedule/), [LC 210 Course Schedule II](https://leetcode.com/problems/course-schedule-ii/), [2026 Google L4 reject post](https://leetcode.com/discuss/post/8265899/google-l4-interview-reject-by-anonymous_-xsc3/)

**Problem.** Standard topological sort: determine whether all courses can be completed and/or return a valid ordering.

**Approach.**
- Kahn BFS indegree or DFS color cycle detection.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
