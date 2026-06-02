# Google DSA Prep: Graphs & Trees (Categorized)

This document contains all Google-asked graph and tree problems. They are ordered from simpler grid/flood-fill traversals to complex connected components (Union-Find), tree-based post-order computations, shortest paths, bipartite matching, and backtracking path optimizations.

---

## 1. Grid Traversals, Flood Fill & Boundary Conditions (BFS/DFS)

### ## 47) Is Coast? (Ocean-connected land)
- **LeetCode Equivalent**: Similar to [LC 1020. Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/) and [LC 1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/)
- **Problem Statement**:
  Given a grid where `'X'` represents land and `'.'` represents water. A water cell `'.'` is considered **ocean** if it is connected (4-directionally) to the grid's boundary. A **coast** cell is defined as any land cell `'X'` that is directly adjacent (4-directionally) to at least one **ocean** cell. Given a coordinate `(r, c)`, determine if it is a coast cell.
- **Input / Output**:
  - Input: `char[][] grid`, `int r`, `int c`
  - Output: `boolean` (true if the cell is a coast cell, false otherwise)
- **Constraints**:
  - `1 ≤ grid.length, grid[i].length ≤ 1000`
  - `0 ≤ r < grid.length`, `0 ≤ c < grid[0].length`
- **Examples**:
  - Grid:
    ```
    . . . .
    . X X .
    . X X .
    . . . .
    ```
    - `isCoast(grid, 1, 1)` → `true` (adjacent to boundary ocean `(0,1)` or `(1,0)`)
  - Grid:
    ```
    . . . . .
    . X X X .
    . X . X .
    . X X X .
    . . . . .
    ```
    - `isCoast(grid, 2, 2)` → `false` (this is water, not land)
    - `isCoast(grid, 2, 1)` → `true` (land adjacent to ocean)
- **Java Starter**:
```java
class Solution {
    public boolean isCoast(char[][] grid, int r, int c) {
        
        return false;
    }
}
```

---

### ## 48) Highest Water Tower Cell Reaching Two Towns via Non-Increasing Paths
- **LeetCode Equivalent**: Similar to [LC 417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)
- **Problem Statement**:
  Given a 2D grid of elevations representing cell heights, and two coordinates `town1` and `town2`. Water can only flow 4-directionally to an adjacent cell if the adjacent cell's height is **less than or equal to** the current cell's height. Find the **highest elevation cell** in the grid from which water can flow (following valid non-increasing paths) to **both** town1 and town2. Return the coordinates of this best cell. If multiple cells have the same maximum elevation, return any.
- **Input / Output**:
  - Input: `int[][] heights`, `int[] town1`, `int[] town2`
  - Output: `int[]` of length 2 representing `{r, c}` of the highest water tower cell. If impossible, return `{-1, -1}`.
- **Constraints**:
  - `1 ≤ heights.length, heights[i].length ≤ 500`
  - `0 ≤ heights[i][j] ≤ 10^9`
- **Examples**:
  - `heights = [[1, 2, 1], [3, 4, 3], [1, 2, 1]], town1 = [0, 0], town2 = [0, 2]`
    - Highest cell is `(1, 1)` of height 4. Paths:
      - `(1,1) [4] → (1,0) [3] → (0,0) [1]` (Valid flow to town1)
      - `(1,1) [4] → (1,2) [3] → (0,2) [1]` (Valid flow to town2)
    - Output: `[1, 1]`
- **Java Starter**:
```java
class Solution {
    public int[] bestWaterTower(int[][] heights, int[] town1, int[] town2) {
        
        return new int[]{-1, -1};
    }
}
```

---

### ## 41) Robot Grid Color → Direction Lookup to Reach Star
- **LeetCode Equivalent**: Similar to grid cycle detection and unique mapping traversals.
- **Problem Statement**:
  Given a 2D grid containing color values (integers `0..7`) and a star cell marked as `-1`. You need to construct a globally consistent mapping of `color → direction` ('U', 'D', 'L', 'R') such that a robot starting at `(0, 0)` and following the direction associated with each cell's color will successfully navigate to the star cell `-1`. The robot must not fall off the grid or get trapped in an infinite loop before reaching the star.
- **Input / Output**:
  - Input: `int[][] grid`
  - Output: `Map<Integer, Character>` mapping colors (0 to 7) to directions ('U', 'D', 'L', 'R')
- **Constraints**:
  - `1 ≤ grid.length, grid[i].length ≤ 100`
  - Exactly one cell is `-1`
- **Examples**:
  - `grid = [[1, 2], [0, -1]]`
    - Mapping `1 → 'R'` (moves robot from `(0,0)` to `(0,1)` of color 2)
    - Mapping `2 → 'D'` (moves robot from `(0,1)` to `(1,1)` which is `-1`)
    - Output: `{1='R', 2='D'}`
- **Java Starter**:
```java
class Solution {
    public java.util.Map<Integer, Character> findLookup(int[][] grid) {
        
        return new java.util.HashMap<>();
    }
}
```

---

## 2. Connected Components & Disjoint Set Union (Union-Find)

### ## 15) Duplicate Groups by Any Shared Property (3 props)
- **LeetCode Equivalent**: Similar to [LC 721. Accounts Merge](https://leetcode.com/problems/accounts-merge/) and [LC 990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/)
- **Problem Statement**:
  You are given a list of elements. Each element has a unique `id` and three string properties (`p1`, `p2`, `p3`). Two elements belong to the same duplicate group if they share **any** of the three properties (e.g. if `A.p1 == B.p1` or `A.p3 == B.p2`). This relationship is transitive: if A matches B, and B matches C, then A, B, and C all belong to the same group. Group and return all elements by their IDs. *(This is also cross-referenced as problem #96).*
- **Input / Output**:
  - Input: `List<Element> items`
  - Output: `List<Set<String>>` containing sets of grouped IDs
- **Constraints**:
  - `1 ≤ items.size() ≤ 10^5`
  - Property strings are alphanumeric.
- **Java Starter**:
```java
class Solution {
    static class E { 
        String id, p1, p2, p3; 
    }
    public java.util.List<java.util.Set<String>> duplicateGroups(java.util.List<E> items) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 29) Circles Grouping (Overlap Connectivity)
- **LeetCode Equivalent**: Similar to [LC 827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/) and overlap Union-Find networks.
- **Problem Statement**:
  You are given a set of circles on a 2D plane, where each circle is represented by `{x, y, radius}`. Two circles are in the same group if they overlap or touch (distance between centers `≤ r1 + r2`). Determine if **all circles** are connected together in a single global group.
- **Input / Output**:
  - Input: `double[][] circles` (where each row is `{x, y, r}`)
  - Output: `boolean` (true if all circles form a single connected component, false otherwise)
- **Constraints**:
  - `1 ≤ circles.length ≤ 10^5`
  - `-10^6 ≤ x, y ≤ 10^6`, `0 < r ≤ 10^6`
- **Examples**:
  - `circles = [[0.0, 0.0, 1.0], [1.5, 0.0, 1.0]]` → `true` (distance 1.5 ≤ 2.0)
  - `circles = [[0.0, 0.0, 1.0], [5.0, 0.0, 1.0]]` → `false` (distance 5.0 > 2.0)
- **Java Starter**:
```java
class Solution {
    public boolean singleCircleGroup(double[][] circles) {
        
        return false;
    }
}
```

---

### ## 44) Dynamic Friend Connectivity with Add/Remove — Earliest All Connected
- **LeetCode Equivalent**: Dynamic Connectivity / Offline DSU with query timeline.
- **Problem Statement**:
  Given a number of users `n` (numbered `0` to `n-1`), and a chronological stream of friendship logs. Each log represents an event: `addFriend(a, b)` or `removeFriend(a, b)` with an associated integer timestamp. Find the **earliest timestamp** at which **all users** become fully connected in a single social network component. If the network is never fully connected or breaks apart, return `-1`.
- **Input / Output**:
  - Input: `int n`, `List<Event> events` (events are pre-sorted by timestamp ascending)
  - Output: `int` earliest timestamp, or `-1` if they never achieve full connectivity
- **Constraints**:
  - `1 ≤ n ≤ 10^5`
  - `1 ≤ events.size() ≤ 2 * 10^5`
- **Java Starter**:
```java
class Solution {
    static class Event { 
        int t; 
        String type; // "add" or "remove"
        int a, b; 
    }
    public int earliestAllConnected(int n, java.util.List<Event> events) {
        
        return -1;
    }
}
```

---

## 3. Trees, Post-order Traversals & Topological Sort

### ## 30) Remove Leaves in Rounds (Tree Stripping)
- **LeetCode Equivalent**: Similar to [LC 310. Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/) leaf deletion processing.
- **Problem Statement**:
  Given a rooted tree, you want to remove all leaf nodes (nodes with zero children) in rounds. However, any leaf node formed *during* a round **cannot** be removed in that same round; it must wait until the next round. Repeat this process until all nodes are removed. Output the list of node values removed in each round.
- **Input / Output**:
  - Input: `Node root` of tree
  - Output: `List<List<Integer>>` containing node values per round
- **Constraints**:
  - The tree contains up to `10^5` nodes.
- **Examples**:
  - Tree: Root `1` has child `2`, `2` has children `3` and `4`.
    - Round 1: Leaf nodes are `3` and `4`. Remove them. (Tree becomes `1 → 2`).
    - Round 2: `2` is now a leaf. Remove it. (Tree becomes `1`).
    - Round 3: Root `1` is leaf. Remove it.
    - Output: `[[3, 4], [2], [1]]`
- **Java Starter**:
```java
class Solution {
    static class Node {
        int val;
        java.util.List<Node> children;
    }
    public java.util.List<java.util.List<Integer>> removeLeavesRounds(Node root) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 40) Managers with Salary < Avg of All Reports
- **LeetCode Equivalent**: Tree-based sub-tree calculation / DFS.
- **Problem Statement**:
  In a company hierarchy represented as a directed tree (directed edges from manager to employee), each employee has an integer salary. Count the number of managers whose salary is **strictly less than** the average salary of all their reports (including both direct reports and indirect reports recursively).
- **Input / Output**:
  - Input: `Map<String, List<String>> reports` (manager ID → list of direct reports), `Map<String, Integer> salary` (employee ID → salary)
  - Output: `int` count of underpaid managers
- **Constraints**:
  - The hierarchy forms a single directed tree structure.
  - Number of employees `N ≤ 10^5`, salary `0 ≤ s ≤ 10^7`.
- **Java Starter**:
```java
class Solution {
    public int countUnderpaid(java.util.Map<String, java.util.List<String>> reports, java.util.Map<String, Integer> salary) {
        
        return 0;
    }
}
```

---

### ## 95) File System Entity Size by ID
- **LeetCode Equivalent**: Tree DFS / Post-order sizing. Similar to [LC 582. Kill Process](https://leetcode.com/problems/kill-process/) sum sizing.
- **Problem Statement**:
  You are given a file system represented as a Map of `EntityID → Entity`. An entity can be either a file or a directory. A file has a fixed size, while the size of a directory is defined recursively as the **sum of sizes** of all its children (which can themselves be files or directories). Given an entity ID `id`, compute its total size.
- **Input / Output**:
  - Input: `Map<Integer, Entity> fs`, `int id`
  - Output: `long` total size of the entity
- **Constraints**:
  - Up to `10^5` entities; no circular folder references.
- **Java Starter**:
```java
class Solution {
    static class Entity {
        int id; 
        String type; // "file" or "dir"
        String name;
        java.util.List<Integer> children; 
        long size;
    }
    public long computeSize(java.util.Map<Integer, Entity> fs, int id) {
        
        return 0L;
    }
}
```

---

## 4. Shortest Path & Dijkstra

### ## 53) Cat & Mouse Safest Path with Blockers
- **LeetCode Equivalent**: Similar to [LC 1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) and path maximum-minimum risk grids.
- **Problem Statement**:
  A grid contains a mouse starting cell `S`, a cat starting cell `C`, and a food cell `F`. Some cells contain water barriers (blocked for the mouse, but the cat can cross water). Find a path for the mouse to reach the food `F` such that the **minimum distance** between the mouse and the cat *at any point on the path* is **maximized**. Return this maximum safeness value (or return the shortest path length if matching specific sub-conditions).
- **Input / Output**:
  - Input: `int[][] grid`, `int[] mouse`, `int[] cat`, `int[] food`
  - Output: `int` safeness factor
- **Constraints**:
  - `1 ≤ grid.length, grid[0].length ≤ 500`
- **Java Starter**:
```java
class Solution {
    public int findDistance(int[][] grid, int[] mouse, int[] cat, int[] food) {
        
        return -1;
    }
}
```

---

### ## 55) Shortest Path to Favorite Cities (Dijkstra from A)
- **LeetCode Equivalent**: [LC 743. Network Delay Time](https://leetcode.com/problems/network-delay-time/) Dijkstra.
- **Problem Statement**:
  You are given a weighted undirected graph of cities and road travel times. Starting from a source city `A`, and a set of favorite cities `[F1..Fn]`, determine which favorite city is reached in the **shortest travel time**, and return that city name.
- **Input / Output**:
  - Input: `Map<String, List<String[]>> graph` (cityA → list of `{cityB, travelTime}`), `String A`, `Set<String> favs`
  - Output: `String` name of the fastest favorite city. If none reachable, return `""`.
- **Constraints**:
  - `1 ≤ cities ≤ 10^4`, travel times are positive integers.
- **Java Starter**:
```java
class Solution {
    public String fastestFavorite(java.util.Map<String, java.util.List<String[]>> graph, String A, java.util.Set<String> favs) {
        
        return "";
    }
}
```

---

### ## 65) Burgers Grid: Shortest Path Visiting All Burgers then End
- **LeetCode Equivalent**: [LC 864. Shortest Path to Get All Keys](https://leetcode.com/problems/shortest-path-to-get-all-keys/) (BFS with Bitmask state).
- **Problem Statement**:
  You are given a grid containing a start cell `'S'`, an end cell `'E'`, multiple burger cells `'B'`, and obstacle cells `'O'`. Find the **shortest path length** starting at `'S'`, visiting **every single burger** `'B'` in the grid, and finally reaching `'E'`. You can step on `'E'` before eating all burgers, but you cannot terminate there.
- **Input / Output**:
  - Input: `char[][] grid`
  - Output: `int` minimum path length, or `-1` if impossible
- **Constraints**:
  - `1 ≤ grid.length, grid[0].length ≤ 30`
  - Number of burger cells `'B' ≤ 8` (suited for bitmask states)
- **Java Starter**:
```java
class Solution {
    public int shortestPathEatAll(char[][] grid) {
        
        return -1;
    }
}
```

---

## 5. Network Flow & Bipartite Matching

### ## 7) Assign Questions to Volunteers by Tags (Max Matching)
- **LeetCode Equivalent**: Maximum Bipartite Matching. Similar to [LC 1349. Maximum Students Taking Exam](https://leetcode.com/problems/maximum-students-taking-exam/)
- **Problem Statement**:
  You have a list of questions, each with a set of tags, and a list of volunteers, each with a set of tags. You can assign at most one question to each volunteer, and at most one volunteer to each question. A match is valid if the question and volunteer **share at least one tag**. Maximize the number of assigned pairs and return the volunteer-question matching pairs.
- **Input / Output**:
  - Input: `List<Item> questions`, `List<Item> volunteers`
  - Output: `List<String[]>` where each item is `{volunteerId, questionId}`
- **Constraints**:
  - Number of items total `N ≤ 10^4`, tags per item `≤ 10`.
- **Java Starter**:
```java
class Solution {
    static class Item {
        String id;
        java.util.Set<String> tags;
    }
    public java.util.List<String[]> assign(java.util.List<Item> questions, java.util.List<Item> volunteers) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 83) Cover Holes with 1×n and m×1 Planks (min planks)
- **LeetCode Equivalent**: Minimum Vertex Cover in Bipartite Graph / König's Theorem.
- **Problem Statement**:
  Given an `m × n` grid where some cells contain holes (`1` representing a hole, `0` representing a solid floor). You want to cover all holes using planks of size `1 × n` (horizontal) or `m × 1` (vertical). Planks can span across multiple adjacent holes but cannot cover solid floor (`0`). Find the **minimum number of planks** required to cover all holes.
- **Input / Output**:
  - Input: `int[][] grid`
  - Output: `int` minimum number of planks
- **Constraints**:
  - `1 ≤ grid.length, grid[0].length ≤ 100`
- **Java Starter**:
```java
class Solution {
    public int minPlanks(int[][] grid) {
        
        return 0;
    }
}
```

---

## 6. Backtracking & Constrained Graph Search

### ## 82) Electricity Network over Roads Only (connect all cities)
- **LeetCode Equivalent**: Minimum Spanning Tree / Steiners Tree. Similar to [LC 1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/)
- **Problem Statement**:
  Given a grid where `-1` represents a city, `1` represents a road, and `0` represents a forbidden zone. You need to connect all cities together by building electrical lines along **roads only**. Find the minimum set of road cells to select to form a fully connected network containing all cities. Output the selected road coordinates.
- **Input / Output**:
  - Input: `int[][] grid`
  - Output: `List<int[]>` coordinates of chosen road cells
- **Constraints**:
  - `1 ≤ grid.length, grid[0].length ≤ 50`
- **Java Starter**:
```java
class Solution {
    public java.util.List<int[]> connectCities(int[][] grid) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 85) Graph Travel Max Weight within Time (A→…→A ≤ 24)
- **LeetCode Equivalent**: Similar to traveling path searches with cost constraints, like [LC 787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
- **Problem Statement**:
  Given a weighted undirected graph of cities and road travel times, and an integer weight assigned to each node city. You start at city `'A'`. You want to find a path that starts at `'A'`, travels to a set of cities, and eventually **returns to 'A'** such that the total travel time is **less than or equal to** a limit (e.g. 24). You want to **maximize the sum of weights** of unique cities visited along the path (each city's weight is counted at most once).
- **Input / Output**:
  - Input: `Map<String, List<String[]>> graph` (cityA → list of `{cityB, travelTime}`), `Map<String, Integer> weight` (cityID → weight), `String start`, `int limit`
  - Output: `int[]` containing `{maxWeight, timeUsed}`
- **Constraints**:
  - `1 ≤ cities ≤ 100`, limit `≤ 100` (fits DFS with backtracking)
- **Java Starter**:
```java
class Solution {
    public int[] maxWeightWithinTime(java.util.Map<String, java.util.List<String[]>> graph,
                                     java.util.Map<String, Integer> weight,
                                     String start, int limit) {
        
        return new int[]{0, 0};
    }
}
```
