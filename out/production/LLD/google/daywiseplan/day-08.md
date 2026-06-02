# Day 8: Focused 4-Question Set

**Date:** Tuesday, June 2, 2026

**Focus:** Design APIs: recent searches, photo iterator, waitlist, URL generator.

## Checklist

- [ ] Q31. Recent Searches Store / Top N Most Recent Searches (Design, Data Structures, LLD / Design - API / Data Structure)
- [ ] Q57. Favorites-First Iterator (No Duplicates) (Design, Data Structures, LLD / Design - Iterator / Generator)
- [ ] Q91. Recipe Creation from Supplies and Dependencies (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Topological Sort / DAG)
- [ ] Q111. Sorted Array Diff Graph Connectivity Queries (Graph, Grid, BFS, DFS, Shortest Path, DSU / Graph - Union-Find / Connectivity)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q31. Recent Searches Store / Top N Most Recent Searches

**Topic:** Design, Data Structures, LLD

**Type:** Design - API / Data Structure

**Original topic file:** [DESIGN_DATA_STRUCTURES_LLD.md](../DESIGN_DATA_STRUCTURES_LLD.md)

**Level / source tag:** Onsite / SWE3

_Links:_ [LC 146 LRU Cache](https://leetcode.com/problems/lru-cache/), [LC 642 Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/)

**Problem.** Design a data structure that stores recent searches.

APIs:
- `focus()` returns `N` most recent searches.
- `search(q)` saves query and returns `N` most recent.

**Variants.**
- Top N most recently searched items like LRU cache.
- Some notes mention autocomplete-like “on typing, save and return top N matches.”

**Approach.**
- If exact recent unique queries: `LinkedHashMap` / doubly linked list + hash map.
- If prefix matching: trie + per-node top list or filter recent list based on constraints.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q57. Favorites-First Iterator (No Duplicates)

**Topic:** Design, Data Structures, LLD

**Type:** Design - Iterator / Generator

**Original topic file:** [DESIGN_DATA_STRUCTURES_LLD.md](../DESIGN_DATA_STRUCTURES_LLD.md)

**Level / source tag:** Google Onsite L4 / L5

_Links:_ not provided in pasted notes.

**Problem.** Given `photos[]` and `favorites[]` with no duplicates, iterate favorites first in favorite order, then remaining photos in original order, without duplicates.

**Input / Output.**
- Input: arrays/lists.
- Output: iterator or list.

**Optimization follow-up.**
- If both arrays are sorted, use merge-like logic / sets depending on required output order.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q91. Recipe Creation from Supplies and Dependencies

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Topological Sort / DAG

**Original topic file:** [GRAPH.md](../GRAPH.md)

**Level / source tag:** Newly added

_Links:_ [LC 2115 Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/)

**Problem.** From recipes, ingredients, and initial supplies, return all recipes that can be produced.

**Approach.**
- Topological sorting from supplies.
- Ingredient -> recipes depending on it.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q111. Sorted Array Diff Graph Connectivity Queries

**Topic:** Graph, Grid, BFS, DFS, Shortest Path, DSU

**Type:** Graph - Union-Find / Connectivity

**Original topic file:** [GRAPH.md](../GRAPH.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
