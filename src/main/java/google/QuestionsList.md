# 📑 Google Interview Master Sheet — L3/L4 (and a few L5/SDE3 where noted)

> Curated from the notes you shared. For each item: Level, Problem, Given details & examples, and LeetCode mapping (Exact / Closest / Technique).
>
>
> If level wasn’t specified in the notes, it’s marked **(Unspecified)**.
>

---

## 🔵 Screening → L4 → (moved to) L3

### 1) Collatz Sequence — **(L4 Screening)**

**Problem.** For `n`, repeat: if odd → `3n+1`, if even → `n/2`, count steps to reach `1`.

**Follow-up.** Make it modular/OOP.

**Verdict.** Lean Hire.

**LeetCode.** *No exact LC.* Technique: simulation + memoization.

---

## 🟢 L3 Interviews

### 2) Minimum Strokes to Paint Fence — **(L3 R1)**

You can paint **horizontal** (add 1 to [l..r]) or **vertical** (a single plank to full). Min strokes.

**Examples.**
- `[1,1,1] → 1` (one horizontal)
- `[3,1,3] → 3` (all vertical better)
- `[4,1,3,2] → 4` (mix)

**Approach shared.** Divide-and-conquer by min height vs all-vertical baseline.

**Verdict.** Strong Hire.

**LeetCode.** *No exact LC.* Closest: **Codeforces 448C – Painting Fence**; Related: **LC 1526** (different op model).

---

### 3) Decode String — **(L3 R2)**

Decode `k[encoded]` with nesting.

**Examples.** `"3[a]2[bc]" → "aaabcbc"`, `"3[a2[c]]" → "accaccacc"`, `"2[abc]3[cd]ef" → "abcabccdcdcdef"`.

**Candidate.** Stack approach; discussed recursion vs stack.

**Verdict.** Hire.

**LeetCode.** ✅ **LC 394. Decode String** (exact).

---

### 4) Googliness / Behavioral — **(L3 R3)**

**Verdict.** Strong Hire.

---

### 5) Lattice Graph Power Propagation — **(L3 Onsite)**

Torch nodes have power **16**; wires start at **0**. Each edge hop reduces power by **1**. From all torches, propagate until no change. Return final grid/graph.

**Model.** Lattice (3D) graph; candidate used BFS from sources.

**LeetCode.** Closest multi-source BFS: **LC 994. Rotting Oranges**, **LC 542. 01 Matrix**.

---

### 6) Assign Questions to Volunteers (by tag match) — **(L3 Onsite)**

Each question has tags; each volunteer has tags. Assign each question to **at most one** volunteer, each volunteer **at most one** question, **maximize assignments** given at least one tag match.

**LeetCode.** *No exact LC.* Technique: **Maximum Bipartite Matching** (Hopcroft–Karp). Closest flavored problems: **LC 1349**, **LC 1066** (different constraints).

---

### 7) CLI: Replace Files with Directories if Fully Selected — **(L3 Onsite)**

Given all files & a selected subset, return **minimal** list where a directory replaces its files **iff** all its files are selected; otherwise list the individual files.

**Example.**
- All: `/a/b/x.txt, /a/b/p.txt, /a/c, /a/d/y.txt, /a/d/z.txt`
- Selected: `/a/d/y.txt, /a/d/z.txt, /a/b/p.txt`
- Output: `/a/d`, `/a/b/p.txt`

**LeetCode.** ✅ **LC 1233. Remove Sub-Folders from the Filesystem** (very close; solution via Trie/sorting + prefix rules).

---

### 8) Shortest Missing Byte Sequence — **(L3 Phone)**

Alphabet initially `{a..f}`; later arbitrary bytes `0x00..0xFF`. Find **shortest substring** (over alphabet) **not present** in the input (up to **4GB**, RAM 8GB).

**Examples.**

- `"abcdefacbeddefd" → "aa"` (since all singles exist; “aa” absent)

**LeetCode.** *No exact LC.* Related: **LC 1461** (all binary codes of size k), **LC 1980** (unique binary string). Technique: de Bruijn/rolling-hash + presence bitset per k; test k=1,2,…

---

### 9) Duplicates by Shared Property (id, p1, p2, p3) — **(L3 Phone, SDE-II)**

Two elements are duplicates if they share **any** of the three properties. Return sets of duplicates.

**LeetCode.** ✅ **LC 721. Accounts Merge** (Union-Find on attributes).

---

## 🟣 L4 Interviews

### 10) Get Max-Score Element w/ “No Consecutive” Rule — **(L4 R1)**

APIs: `addElement(content, score)`, `getElement()` returns current max score **but cannot return same content consecutively**; after return, its score **decrements by 1** and stays in the pool. Empty system → `""`.

**Example.** apple(5), banana(3), cherry(5) → apple → cherry → apple → cherry → banana …

**Verdict.** Lean Hire.

**LeetCode.** Related greedy with heap & cooldown: **LC 358** (rearrange string k-apart), **LC 621** (task scheduler).

---

### 11) Log System Sorted by Timestamp, Stable Ties — **(L4 R2)**

Timestamps as `"DD-MM-YY HH:MM:SS"`. Maintain sorted-by-time logs; if same time, keep **insertion order**. Return as array anytime.

**Verdict.** Lean Hire (noted complexity & type mistakes).

**LeetCode.** ✅ **LC 635. Design Log Storage System** (close in spirit; your solution: parse to epoch (long), keep **stable ordered structure** + tie-breaking index).

---

### 12) Googliness / Behavioral — **(L4 R3)**

**Verdict.** Strong Hire.

---

## 🟨 Other Google Questions from your notes (mixed levels)

> We keep the original wording as much as possible and include given examples.
>

### 13) Google Onsite Round 2 (L3): Torch/Wire + Matching Tags (two sub-questions)

- **(a) Power lattice** — *covered above (#5)*
- **(b) Matching questions to volunteers** — *covered above (#6)*

---

### 14) Google Onsite: CLI Compression of Selected Paths — *(covered above #7)*

---

### 15) Byte Sequence Missing, Large Input — *(covered above #8)*

---

### 16) Find Incompatible Pair of Unit Tests (testRunner) — **(Unspecified)**

Given `testRunner(subset)` returning pass/fail; all individual UTs pass; running **all** fails. Find **one pair** that causes failure. Optimize when `testRunner` is **O(n)** for `n` tests.

**Idea.** Group-testing / divide-and-conquer / delta-debugging to isolate minimal failing pair; when `O(n)` per run, minimize subset sizes + reuse results.

**LeetCode.** *No exact LC.* Technique: binary search on subsets; pair localization.

---

### 17) Validate Equation Syntax (+, -, a–z, (, ), =) — **(Unspecified)**

Check if the equation is **syntactically valid**; e.g.

- Valid: `a + x = b + (c + a)`

- Invalid: `a + x =` (RHS missing)

- Invalid: `a + -x = a + b` (unary `-` present—define allowed/grammar)

**LeetCode.** Related parsing: **LC 224/227/772** (Basic Calculator I/II/III).

---

### 18) Expression Add Operators with Parentheses to Hit Target — **(L3 Phone)**

Input numbers (e.g., `2 3 4`), include ops `+ - * / ( )`, target `20`. Return **all expressions** that evaluate to target.

**LeetCode.** ✅ **LC 282. Expression Add Operators** (no division), **LC 241. Different Ways to Add Parentheses**.

---

### 19) Recover Coins from DP Array (Coin Change Ways) — **(Unspecified)**

Given the **DP array of #ways** to form sums up to `target`, reconstruct **a valid set of coin denominations** that could yield that DP.

**LeetCode.** *No exact LC.* Technique: generating functions / inverse construction.

---

### 20) Valid Parentheses with Unique Pairing Constraint — **(Onsite)**

As **LC 20**, plus: each open bracket must have a **unique** closing candidate (no ambiguity).

**LeetCode.** ✅ **LC 20. Valid Parentheses** (base), extend with uniqueness rule via stack + “branch-count must be ≤1” check per opening.

---

### 21) Tournament Valid Draw — **(L5 Round 1)**

Players ranked `1..N`. Knockout bracket is a **draw** ordering. A draw is **valid** if in **each round**, best plays worst, 2nd best vs 2nd worst, etc—**without reordering** within the round brackets implied by the initial draw.

**Examples given** show valid/invalid per round.

**LeetCode.** *No exact LC.* Technique: simulate rounds, check pairs per block.

---

### 22) Allot People to Apartment Rooms — **(L3)**

Each **Apartment** has `numRooms ≥ 1`; each **Person** wants either **solo (1BHK)** or **housemates (2+ rooms)**. Assign each person to **exactly one room**; obey apartment capacities.

**LeetCode.** *No exact LC.* Technique: **flow / bipartite matching with capacities**.

---

### 23) Max Score Jumping Array — **(Unspecified)**

Score of a jump equals **distance × value at landing index**. Unlimited jumps to reach end with **max total score**.

**Example.** `[3,12,9,10] → 32` via `0→1→3` (12 + 20).

**LeetCode.** *No exact LC.* Technique: DP with best prefix candidates; potential monotonic deque if convexity appears.

---

### 24) Stream of Floats: Find Triples within Distance D — **(Unspecified)**

Maintain streaming uniques; on seeing new `x`, if there exist `a,b` with `|a-b|≤D`, `|b-c|≤D`, `|a-c|≤D`, output triple and remove them.

**LeetCode.** *No exact LC.* Technique: balanced BST / ordered map + sliding clusters (bucketization by `D`).

---

### 25) Minimum Positive Number Not Formable from Array using `+ - * / ( )` — **(L3 Phone Screen)**

Pick any subset & order, place ops/parentheses, evaluate; find **smallest positive integer not representable**.

**LeetCode.** *No exact LC.* Technique: expression search with memo; heavy; small N feasible.

---

### 26) JSON Version Store: Design API / Diff Storage — **(Senior, System Design)**

Store **all versions** but reduce storage via **diffs**. Discuss APIs, format, patching (3-way merge?), compression, GC, compaction.

**LeetCode.** N/A (System Design).

---

### 27) Tiling m×n Grid with Given Shapes (Rect/Square/L-shape) — **(SDE-3 Phone)**

Shapes can rotate/flip; can we tile without gaps? Return boolean.

**LeetCode.** *No exact LC.* Related tiling: **LC 1240. Tiling a Rectangle with the Fewest Squares**. Technique: **Exact Cover (DLX/Algorithm X)** or DFS with bitmasks.

---

### 28) Neighborhoods Refill with Sorted & Capacity Constraints — **(L3 Phone)**

Given arrays (neighborhoods) of house numbers; rearrange across neighborhoods so that **within each neighborhood numbers are sorted**, **no duplicates inside a neighborhood**, and **each neighborhood keeps its original capacity**.

**LeetCode.** *No exact LC.* Technique: global multiset + round-robin fill by freq (like **reorganize string**), then per-array sort.

---

### 29) Generate Substrings List that Rebuilds Original — **(L3 Phone)**

Given a string, produce list of substrings so that concatenating them in order rebuilds original; if a substring hasn’t been seen before, **add it to the list**.

**Examples.**
- `"GOOOOOOGLE" → ["G","O","OO","OOO","GL","E"]`
- `"GOOOOOOGLEG" → ["G","O","OO","OOO","GL","E","G"]`

**LeetCode.** *No exact LC.* Technique: greedy extend longest-seen, then add new token (dictionary-building).

---

### 30) Max Subarray Sum where `nums[i] == nums[j]` — **(Onsite Follow-up)**

Return indices `[i,j]` maximizing subarray sum with equal endpoints.

**LeetCode.** *No exact LC.* Technique: for each value, track best `prefixMin` at first occurrence to maximize range sum; akin to Kadane with buckets.

---

### 31) Bank Serving Customers with Start Anywhere, Can Refuse Some — **(Onsite)**

Initial balance `B0`; transaction array `+/-`; choose a starting index, may **skip customers before** starting, then must serve sequentially **until impossible** (balance would go negative). Maximize customers served.

**LeetCode.** Related: **LC 134. Gas Station** (circular feasibility), **LC 2389** (max subseq length under sum)—but this is different.

---

### 32) Frog Moves Left/Right up to L/R Max Steps — **(Phone)**

Frog on integer line; from a position can move **0..L** left on odd moves, **0..R** right on even moves; find **min steps** from start to destination.

**LeetCode.** *No exact LC.* Technique: BFS on states (pos, parity) with bounded transitions.

---

### 33) Odd/Even Jump Game with Value+1 Search & In-place Updates — **(Unspecified)**

Array `A`, value `X`, start `S`. 1st move odd: jump to **first index left** with value `A[idx]+1`; even move: **first index right** with value `A[idx]+1`. After each jump, set previous position to `X`. Stop if no move; detect infinite loops.

**Example.** Provided (ends at index `1`), and `[2,1], X=2, S=1` is infinite.

**LeetCode.** *No exact LC.* Technique: precompute next greater positions on left/right; simulate with visited state detection.

---

### 34) Circles Form One Group (overlap connectivity) — **(Onsite set)**

Circles by center & radius; circles overlap means connected; do **all** belong to one connected component?

**LeetCode.** Related: **LC 547. Number of Provinces**, **LC 200. Number of Islands** (connectivity); here edge if distance ≤ r1+r2.

---

### 35) Repeated Leaf Removal with “Fresh Leaf” Constraint — **(Onsite set)**

In a rooted tree, iteratively remove current leaves and collect levels. **Constraint**: a node that becomes a leaf cannot be removed **immediately** unless no other choice.

**LeetCode.** Related: **LC 366. Find Leaves of Binary Tree** (but without the “fresh” constraint).

---

### 36) Recent Search Data Structure — **(Onsite set)**

Show top **N recent searches** on empty input; on typing, save and return top **N** matches.

**LeetCode.** Related design: **LC 642. Design Search Autocomplete System**.

---

### 37) Min-Cost Path — **(Onsite set)**

Generic “find minimum cost path” on graph/grid.

**LeetCode.** Many (Dijkstra/0-1 BFS): **LC 743**, **LC 1631**, etc.

---

### 38) Environment Variable Expansion with Nesting and Escapes — **(Onsite set)**

Given map like `{USER=>admin, HOME=>/%USER%/home}`, expand `%VAR%` with support for nesting, and `%%` → literal `%`. Detect cycles.

**LeetCode.** *No exact LC.* Technique: DFS on dependency graph, memoization, cycle detection.

---

### 39) Active Users Count over [0,N) from Inclusive Ranges — **(Onsite set)**

Input: sessions `(start,end)` inclusive. For each integer t in `[0,N)`, count active users.

**Example.** `[(0,3),(1,4)], N=7 → 0:1, 1:2, 2:2, 3:2, 4:1`.

**LeetCode.** Line-sweep: **LC 253**, **LC 1109** (difference array).

---

### 40) Arrange Students to Minimize Cheating (adjacent equals) — **(Onsite set)**

Rearrange sequence of exam IDs to minimize adjacent equal exams.

**LeetCode.** Related: **LC 767. Reorganize String**, **LC 358**.

---

### 41) Replace Files with Directories in Subset — **(Onsite set)**

*Duplicate of #7 / #14* — same problem.

**LeetCode.** ✅ **LC 1233**.

---

### 42) Streaming RPC Requests: Earliest Timeout Detection — **(Onsite set)**

Logs `{id, t, type(start|end)}`; with timeout `T`, at what earliest time can we **declare** a request timed out?

**Example.** Given, output `{1,6}` because at time 6 start(1) exceeded T without end.

**LeetCode.** *No exact LC.* Technique: min-heap keyed by expiry; sweep timestamps.

---

### 43) Choose 5 Strings of Len=5 to Cover 25 Unique Chars — **(Onsite set)**

From list of 5-char strings, pick **5** such that **all 25 chars are unique** across them.

**LeetCode.** *No exact LC.* Technique: backtracking/bitmask; akin to **maximum clique** on compatibility graph.

---

### 44) Sort Only Odd Elements, Keep Evens in Place — **(Onsite set)**

Sort the **odd-valued** elements increasing **without moving evens** (stable positions).

**LeetCode.** Closest: **LC 922** (parity positioning) but different; here: collect odd indices, sort, write back.

---

### 45) Consultant Schedule Across Two Cities with Travel Days — **(Onsite set)**

Earn `Ai` in city A or `Bi` in city B; you may **travel (T)** a day (earn 0). Maximize total earning; output schedule string of `A/B/T`.

**LeetCode.** Related DP: **LC 568. Maximum Vacation Days** (travel transitions).

---

### 46) Count Managers with Salary < Avg of (Direct+Indirect) Reports — **(Onsite set)**

Given org edges and salaries, count managers whose salary is below **average of all descendants**.

**LeetCode.** *No exact LC.* Technique: post-order DFS accumulating (sum, count).

---

### 47) Robot Routing by Color→Direction Lookup to Reach Star — **(Onsite set)**

Grid 10×10 with 8 colors (0..7) and one star (-1). Provide a **color→move** lookup mapping so robot reaches star deterministically.

**LeetCode.** *No exact LC.* Technique: construct functional graph on colors; ensure path funnels to star region.

---

### 48) Streaming Mean of Last N Excluding Largest K — **(Onsite set)**

Maintain rolling mean over last `N` elements **excluding top K**.

**Example.** `N=5,K=2` on `[20,2,-2,0,10,1,5,-2,0]` → mean over `[1,-2,0]`.

**LeetCode.** Related: ✅ **LC 1825. Finding MK Average** (excludes K smallest & K largest). Here exclude top **K only** → two-heaps + balance + queue.

---

### 49) LIS where Differences Are Increasing — **(Onsite set)**

Find LIS such that the **consecutive differences** form a **strictly increasing** sequence.

**LeetCode.** *No exact LC.* Technique: DP on `(i,lastDiff)`; coordinate compress diffs; O(n² log n) typical.

---

### 50) Social Network: Earliest All Become Friends with Add/Remove — **(Onsite set)**

Base: **LC 1101** (earliest moment all connected) with only **Add Friend**. **Follow-up:** also have **Remove Friend** events; users may connect/disconnect multiple times.

**LeetCode.** Base: ✅ **LC 1101**; With removals: dynamic connectivity (offline divide-and-conquer on segments or DSU with rollback).

---

### 51) Sparse Bit Array with `query(L,R)` = “any 1?” — **(Onsite set)**

Find all positions of 1s using sublinear queries, constant extra space, no recursion.

**LeetCode.** *No exact LC.* Technique: exponential/binary search windowing to locate next 1, iterate.

---

### 52) On-call Rotation → Non-overlapping Timeline with Assignees — **(Android SDE3 Phone)**

Given schedules: `name, start, end`, output contiguous **non-overlapping** ranges with **active list of people**.

**Example.**

```
1 5 Abby
5 6 Abby, Ben
6 7 Abby, Ben, Carla
7 10 Abby, Carla
10 12 Carla
15 17 David
```

**LeetCode.** Line-sweep / calendar merge: **LC 352**, **LC 986** (interval lists).

---

## 🧭 Quick Index by Technique

- **Union-Find/Connectivity:** #9, #21, #34, #50 (add/remove with rollback)
- **Greedy + Heaps:** #10, #40, #44, #48
- **Bipartite Matching / Flow:** #6, #22
- **Line Sweep / Difference Array:** #39, #52, #42
- **Parsing/Stacks/Recursion:** #3, #17, #18, #38
- **DP:** #2, #18, #23, #45, #49
- **BFS/Graph:** #5, #32, #37
- **Trie/Filesystem:** #7/#14/#41
- **Exact Cover / Backtracking:** #27, #43, #29

---

## ✅ Notes & Corrections

- Earlier draft mistakenly referenced **LC 1320** for fence painting — corrected. There’s **no exact LC**; closest is **CF 448C**.
- Where level wasn’t stated in the note, it’s **(Unspecified)**. If you recall the exact level for any, tell me and I’ll tag it precisely.

---

## 🗂️ Summary Table (Abbrev.)

| # | Title | Level | LeetCode Mapping |
| --- | --- | --- | --- |
| 1 | Collatz Steps | L4 Screen | None (simulation) |
| 2 | Paint Fence Min Strokes | L3 | CF 448C / LC 1526-ish |
| 3 | Decode String | L3 | **LC 394** |
| 5 | Lattice Power BFS | L3 | LC 994/542 |
| 6 | Tag Matching Assign | L3 | Max Bipartite Matching |
| 7 | CLI Replace Paths | L3 | **LC 1233** |
| 8 | Shortest Missing Byte | L3 | LC 1461/1980 (related) |
| 9 | Dups by Property | L3 | **LC 721** |
| 10 | No-Consecutive Max | L4 | LC 358/621 (related) |
| 11 | Stable Log System | L4 | **LC 635** |
| 18 | Expr to Target | L3 | **LC 282**, LC 241 |
| 21 | Valid Tournament Draw | L5 | None |
| 22 | Allot Apartments | L3 | Flow/Matching |
| 23 | Max Score Jumps | — | DP |
| 24 | Stream Triples ≤ D | — | Buckets/BST |
| 25 | Smallest Positive Unformable | L3 | — |
| 27 | Tiling Grid w/ Shapes | SDE-3 | Exact Cover, LC 1240 (related) |
| 28 | Neighborhoods Refill | L3 | — |
| 29 | Build Substrings List | L3 | — |
| 30 | Max Subarray (equal ends) | — | — |
| 31 | Bank Serve Customers | — | LC 134 (related) |
| 32 | Frog L/R Steps | — | BFS states |
| 33 | Odd/Even Jump Game | — | Monotone next + sim |
| 34 | Circles Single Group | — | DSU |
| 35 | Repeated Leaf Removal+ | — | LC 366 (related) |
| 36 | Recent Searches DS | — | LC 642 (related) |
| 39 | Active Users per t | — | Line sweep |
| 40 | Minimize Cheating | — | LC 767/358 |
| 42 | Earliest Timeout | — | Heap over expiry |
| 43 | 5×5 Unique Chars | — | Backtracking |
| 44 | Sort Only Odds | — | Collect/sort/writeback |
| 45 | Two-city Earnings + Travel | — | LC 568 (related) |
| 46 | Manager < Avg Reports | — | Tree DP |
| 48 | Rolling Mean excl top K | — | **LC 1825** (MK avg variant) |
| 49 | LIS w/ Increasing Diffs | — | DP |
| 50 | Earliest All Friends (+Remove) | — | **LC 1101** base + DSU rollback |
| 51 | Sparse Bit Array via query | — | Binary search windows |
| 52 | On-call to Non-overlap | SDE3 | Line sweep |

---

**Strong prep tip, Sajal:** For each bucket above, implement one polished reference solution (clean API + tests). That compounding quality shows in L3/L4 loops. You’ve got this 💪

---

## 🟧 Newly Added Google Questions

### 53) Coast Detection in Grid (Land vs Ocean vs Lake)

**Problem.**
- Grid: `'X'` = land, `'.'` = water.
- A `'.'` is **ocean** if unbounded on at least one side.
- **Coast** = any land directly adjacent to ocean water.
- Bounded water (all sides land) = lake.
- Task: `isCoast(i,j)` returns whether coordinate is a coast.

**Example.**

```
x x x x x x
. . . x . x . .
. . . x x . x .
```

`isCoast(1,3) → True`.

**LeetCode.** Related: **LC 200. Number of Islands**, **LC 463. Island Perimeter**, with ocean-marking BFS.

---

### 54) Water Tower Placement for Two Cities in Height Matrix

**Problem.**
Given `heights[][]` and coordinates of two towns. Find **highest cell** where tower can be placed such that **downhill (non-increasing)** paths exist from tower to both towns. No diagonal moves allowed.

**Example.**

```
heights =
[ [4,9,7,6,5],
  [2,6,5,4,3],
  [6,5,1,2,8],
  [3,4,7,2,5] ]

town1 = [1,4], town2=[3,1] → Output [0,1] (height 9)
```

**LeetCode.** *No exact LC.* Technique: reversed BFS (flow downhill) intersection; similar to **LC 417. Pacific Atlantic Water Flow**.

---

### 55) Stones Jump Game – Max Score (<O(n²))

**Problem.**
Array of stone values. Starting from index0, can jump to any later index. **Score = (j-i) × stone[j]**, sum over sequence until end. Maximize score.

Naïve DP is O(n²). Need faster.

**Examples.**
- `[1,2,3,4,5] → 20` (jump to last: 4×5).
- `[5,4,3,2,1] → 10` (jump stepwise: 4+3+2+1).
- `[3,5,2,8,1] → 25`.

**Candidate Code.** Provided O(n²) DP.

**LeetCode.** *No exact LC.* Closest: **LC 1696. Jump Game VI** (DP with deque optimization).

---

### 56) Subtract Subsequences in Queries to Reduce Array to Zero

**Problem.**
Array `arr`, queries `[l,r]`. Each query: may subtract `1` from any subsequence inside `[l,r]`. After all queries, can array become all zeros?

**Example.**

```
arr=[1,2,3]
queries=[[0,1],[1,2],[0,2],[1,2]]
Output=true
```

**LeetCode.** *No direct LC.* Related: **difference array / range update validation**.

---

### 57) Find Common Free Time Intervals across Persons

**Problem.**
Given blocks `{personId, start, end}`, and `totalTime`. Find intervals where **all persons are free**.

**LeetCode.** ✅ **LC 759. Employee Free Time**.

---

### 58) Job Scheduling with Max CPUs Constraint

**Problem.**
Each job `{start,duration,cpuNeed}`, maxCpu capacity. Return true if schedulable without exceeding capacity.

**LeetCode.** ✅ **LC 253. Meeting Rooms II**, ✅ **LC 759**, and variant **resource-constrained scheduling**.

---

### 59) Max Ancestor Value for Each Leaf

**Problem.**
Binary tree (later general n-ary). For each leaf, report **maximum of ancestors and itself**.

**Example.**

```
    4
   / \
  5   3
 /   / \
1   2   6
Output: {1:5, 2:4, 6:6}
```

**LeetCode.** Related: **LC 1026. Maximum Difference Between Node and Ancestor**.

---

### 60) Extended Decode String with `{}` Repeaters

**Problem.**
Input with `[string]{k}` meaning repeat previous `[string]` k times. Nested supported.

**Examples.**
- `"ab[cd]{2} → abcdcd"`
- `"def[ab[cd]{2}]{3}ghi → defabcdcdabcdcdabcdcdghi"`

**LeetCode.** ✅ **LC 394. Decode String** (extend to `{}` syntax).

---

### 61) Recursively Delete Leaves in Multi-tree

**Problem.**
Remove leaves iteratively, returning nodes in order removed. Multi-children tree.

**Example.**

```
1
/ | \
2 5 3
/ \  |
7 4  9
    |
    8
Output: [7,8,9,3,4,5,2,1]
```

**Candidate.** Proposed bottom-up depth grouping. Interviewer hinted possible O(n) without storing depth arrays.

**LeetCode.** Related: **LC 366. Find Leaves of Binary Tree**.

---

### 62) Recipe Creation from Supplies & Dependencies

**Problem.** From recipes + ingredients + initial supplies, return all recipes that can be produced.

**LeetCode.** ✅ **LC 2115. Find All Possible Recipes from Given Supplies** (exact).

---

### 63) Song Shuffler with “No Repeat in Last K” Constraint

**Problem.**
Design shuffler: each `playNext()` returns random song from list, **not played in last k turns**. All eligible songs must have **equal probability**.

**Example.** songs=[A,B,C,D], k=2.

Valid sequence: C → A → B → C …

**LeetCode.** *No exact LC.* Technique: sliding window + random uniform sampling; related to **reservoir sampling / queue**.

---

---

## 🟥 Newly Added Google Questions (Aug 2024 Set)

### 64) Flipping an Image II (Bitwise In-Place, Bytearray)

**Problem.**
Given a grayscale image stored in a `bytearray`, flip the image **horizontally** in place.

- Each bit = 1 pixel.

- Width `w` (bits, multiple of 8), height `h`.

- Input size = `(w*h)//8` bytes.

- Must reverse bits row-wise without extra array.

**Example.**
Row1: `10100011 00001111` → flipped: `11110000 11000101`.

**Constraints.**
- 8 ≤ w ≤ 10⁴, 1 ≤ h ≤ 10³.

- Optimize time & space.

**LeetCode.** Related: **LC 832. Flipping an Image**, but here at **bit-level**.

---

### 65) Vertical Line Dividing Rectangles into Equal Area (Screening)

**Problem.**
Given rectangles as `[x1,y1,x2,y2]` (can overlap). Find a vertical line `x=const` dividing plane into equal left/right areas.

- If line lies through a rectangle, split area accordingly.

- Use binary search on `x`.

**Complexity.** O(N log X), X = max x-coordinate.

**LeetCode.** *No exact LC.* Related to **sweep-line geometry problems**.

---

### 66) Cat and Mouse Safest Path in Grid

**Problem.**
Grid N×N with Mouse, Cat, Food. Values: `0=Land`, `1=Water`.

- Mouse cannot step on water, Cat can.

- Find **minimum maximum safest distance** so mouse can reach food avoiding cat.

**Candidate.** Gave PriorityQueue + Binary Search solution O(N² log N). Interviewer wanted O(N²).

**LeetCode.** ✅ **LC 2812. Find the Safest Path in a Grid** (variation).

---

### 67) Count Words with Given Prefix in Sorted Array (Phone Screen L4)

**Problem.**
Given sorted array of strings, and a prefix, count how many words start with prefix.

**Example.**

```
arr=["bomb","book","g","gift","go","goal","goat","gum","xray","yellow","zebra"]
prefix="go" → Output=3
```

**Candidate Approach.**

- Linear O(n*k).
- Tried one binary search + two pointers → still O(n).
- Expected: binary search left bound, right bound → O(log n*  k).

**LeetCode.** ✅ **LC 208. Implement Trie**, ✅ **LC 14. Longest Common Prefix**, and binary search technique on sorted strings.

---

## Google Onsite Round 2 (Aug 03, 2024)

- **Problem**: Map of cities with roads (weighted). From city A, given favorite cities [F1..Fn], decide which favorite city is reachable fastest.
- **Approach**: Dijkstra’s with PQ. Discussion about time complexity: (V+E)logE vs (V+E)logV.

## Google L3 Phone Screen (Aug 01, 2024)

- **Problem**: Given list of elements, each with id and 3 properties. Two elements are duplicates if they share same properties. Return groups of duplicates.
- **Example**:
    - E1: id1, p1, p2, p3
    - E2: id2, p1, p4, p5
    - E3: id3, p6, p7, p8
    - Output: {{id1, id2}, {id3}}

## Google Onsite L4 (Jul 31, 2024)

1. **String Resolver**: Replace variables (#var#) recursively with values.
    - Example: b -> #a#src, a -> data#c#, c -> base.
    - Input: hello#b# → Output: hellodatabasesrc.
2. **Max Transactions**: Given array of tx amounts (negative=deposit, positive=withdraw), and initial T. Find max transactions possible before failing.
3. **Favourite Photos Iterator**: Return favourite photo ids first (in order), then others, no duplicates. Optimize if both sorted.
4. **Message Stream**: Given stream of timestamped messages, discard duplicates within 10s window (both t and t+10 are discarded).
5. **Combination Lock**: 3-digit circular lock with user combo, bypass combo, and tolerance. Count distinct valid combinations.
6. **Substring in Internal/Leaf Structure**: Given composite structure of leaf nodes (strings) inside internals, implement substring extraction with start and length.

## Google Onsite L5 India (Aug 02, 2024)

- **Round 1**: Photos iterator (favorites first).
- **Round 2**: Stream message deduplication within 10s.
- **Round 3**: Combination lock (circular digits, tolerance, multiple valid combos).
- **Round 4**: Tree-like structure with internal and leaf nodes, implement substring query.

## Google Phone Screen (Apr 26, 2024)

- **Problem**: Count distinct combinations (length=3) from two arrays arr1, arr2 with tolerance t. Wrapping bounds. Order matters.
- **Follow-up**: Counting overlaps. Math-based solution with O(2t) space.

## Google Interview Question (Aug 02, 2024)

- **Problem**: Transform integer S → D using ops: add A[i], subtract A[i], xor A[i]. Each op = 1 move. Return min moves or -1.
- **Example**: A = [6,2,7,7], S=10, D=21.

## Google Interview Onsite 1 (Aug 01, 2024)

- **Problem**: Given array of line segments, can a line from origin (0,0) be drawn that avoids all segments?
- **Follow-up**: Line through (x,y).
- **Approach**: Convert to angle intervals, check gaps.

## Google Screening L4 (Jul 30, 2024)

- **Problem**: Mean of last K elements in stream. Optimize enqueue to O(1).
- **Follow-up**: Exclude largest X elements. Use SortedList + curSum + sumLargestX. Similar to [MK Average](https://leetcode.com/problems/finding-mk-average/).

## Google Screening Interview (Jul 29, 2024)

- **Problem**: Stream of integers until zero. At zero, output a number according to frequency distribution.
- **Naive**: O(n) space.
- **Optimized**: Hashmap of counts, probability array, binary search.
- **Similar**: [Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/).

## Google India OA (Jul 27, 2024)

- **Problem**: Given binary string of length n, compute number of distinct decimal values from all subsequences. Answer mod 1e9+7.

## Google L3 Interview Round 1 (Jul 25, 2024)

- **Problem**: Remove parentheses from formula.
- **Examples**:
    - a-(b+c) → a-b-c
    - a-(a-b) → b

## Google Phone Screen (Jul 21, 2024)

- **Problem 1**: Array difference (with duplicates). Return A-B and B-A.
- **Problem 2**: Ordered difference (preserve input order).

## Google OA 2024 SWE (Jul 17, 2024)

- **Problem**: Given array A, integers N,K. Subsequence B of size 2K.
- Define F(B) = (B1|…|BK) XOR (BK+1|…|B2K).
- Find max F(B).

## Google Mock L4 (Jul 16, 2024)

- **Problem**: Build electricity network on map (matrix).
    - 1 = road, 0 = farm (blocked), -1 = city.
    - Connect all cities with minimum cost, only via roads.

## Recent Google Interview (Dec 08, 2024)

- **Problem**: m×n grid with holes. Cover all with planks: 1×n or m×1. Minimize plank count.

## Reference Problem: [Rectangle Area (LeetCode 223)](https://leetcode.com/problems/rectangle-area/)

- Compute total area of two rectilinear rectangles.

## Google OA & Interviews (2024 mid-year additions)

### Google OA 2024 - Subsequence Check

- Problem: Check if string A contains string B as subsequence, with at most 1 change allowed in B (not the first char). Return starting index (1-based) if found else -1.
- Example:
    - A = “abcbc”, B = “cbe” → Answer = 3
    - A = “lhs”, B = “rhs” → Answer = -1

---

### Google Phone Screen - Triplet Distance in Stream

- Problem: For a stream of floating points, return any 3 points whose pairwise distance < d, then remove them from memory.
- Example: Stream = [1.0, 2.0, 8.0, 12.0, 3.0], d = 3 → Output = [1.0, 2.0, 3.0]

---

### Partition Equal Subset Sum (Google variant)

- Modification: Works with negative numbers.
- Task: Partition array into two subsets with equal sum (LC 416 variant). Optimized solution expected.
- Follow-ups: Return both subsets / return all such partitions.

---

### Google OA - Shortest Path after Eating Burgers

- Problem: NxN grid with S (start), E (end), B (burgers), O (roads). Must eat all burgers before reaching E. Find shortest path.
- Example:

```
BOOB
OSOO
OOOE
BOOO
```

Answer = 11

---

### Google Telephonic Screening (Hyderabad L4)

- Problem: Given ranges of IPs with associated country. For an input IP, return country.
- Example: {1.1.0.1 - 1.1.0.10} → IND; Query: 1.1.0.5 → Output = IND.

---

### Google SWE Early Career (OA + Onsite)

- OA: Given positive integer x, return list of values whose squares sum to x. Follow-up: shortest list (use DP).
- Onsite:
    1. Assign cars to reservations (min #cars). Like interval allocation with heap.
    2. Compare text content of HTML string vs raw string. Follow-ups: count tags / handle streaming input.
    3. Find N most talkative users from chat logs. Optimizations: bucket sort.

---

### Google OA - Palindromic Triples

- Problem: Count triples of palindromes (i1 ≤ j1 < i2 ≤ j2 < i3 ≤ j3). Answer may be large (use long long).

---

### Google Phone Screen - Subsets LCM

- Problem: Count subsets of array where LCM of subset divisible by k.
- Example:
    - arr = [1, 7, 3, 2, 5], k = 7 → Output: 16
    - arr = [2, 3, 1, 6, 1], k = 6 → Output: 20

---

### Google OA - Binary Tree to String

- Problem: Given Node-based binary tree, return string representation (preorder traversal style).

---

### Google Phone Screen - Expression Evaluator

- Problem: Evaluate string with operators `add`, `mul`, `sub`, `pow`, `div`. Inputs are floating point.
- Example: `mul(2e3, sub(4,2))` → 4000

---

### Google Onsite (2022) - Robot Sort with Empty Slot

- Problem: Sort boxes with single empty slot at end using robot (like sliding puzzle). Optimize better than O(n^2).

---

### Google Onsite Final - Org Manager Queries

- Queries:
    1. `manager a b` → a is manager of b
    2. `peer a b` → a and b share same manager
    3. `is_manager a b` → return true if a manages b

---

### Google L3 (Bangalore) Interview

- Round 1:
    - Sort array where each element ≤ k positions from sorted place.
    - Tree operations: addNode, getRandomNode, getRandomLeafNode (all O(1)).
- Round 2:
    - Count sum of all subarrays that form AP with diff = ±1.
- Round 3:
    - Max consecutive occurrence of k. Variations with modifications.

---

### Google Onsite - Musical Notes Sequences

- Problem: Generate sequences of {1,2,3} summing to 12 with valid transitions (dict). Use DFS. Return all valid sequences.

---

### Google Phone Screen - File System Entity Size

- Entity struct: file/directory with size & children. Compute size of given entity. Follow-ups: static data storage, GB scale, recursive directories.

---

### Google Interview - Address List Lookup

- Problem: Given addressList = [(1,“A”,“AZ”,“AZZ”), …], query with null wildcards. Return if exists in O(1).

---

### Google Interview - Bomb Detonation Pairing

- Problem: Explode bombs in pairs (i,j) if ranges don’t overlap. Maximize total radius.
- Example: bombs = [3,1,1,1,3] → Output = 6.

---

### Google Phone Screen - Offset Commit Order

- Problem: Given offsets list, return commit order (greedy). If offset can’t be committed yet, mark -1.
- Example: Input [2,0,1] → Output [-1,0,2].

---

### Google Phone Screen - Largest k-digit Number

- Problem: From array, pick k digits in order to form largest number.
- Example: arr=[4,9,0,2], k=2 → Output=92.

---

### Google Interview - Bank Transactions

- Problem: Bank with X funds, N customers. Each deposits/withdraws. Bank can skip some, but once started must serve consecutive customers until break. Max #served.

---

### Google Phone Screen - Car Booking Intervals

- Problem 1: Check overlap between two intervals.
- Problem 2: Assign min cars to service all time intervals.

---

## 🟦 Newly Added Google Questions (May 2024 – Jan 2025)

### 68) Longest Increasing Subsequence with Difference = 1 (Onsite R1)

Asked in onsite: find LIS where consecutive elements differ by exactly 1.

Solved in O(n) but coding errors in doc caused issues.

**Similar LC:** ✅ **LC 673. Number of LIS**, ✅ **LC 300. LIS** (adapt difference).

---

### 69) Reorganize Items in Sections (Phone Screen)

2D array, each row = section. Reorganize items so each section is sorted and unique. Structure of rows/cols preserved.

Candidate: O(mn + n log n). Asked for optimized.

---

### 70) Balance Parentheses with Deletions at Digit Index (Phone Screen)

String contains parentheses + digits. At digit index `d`, must delete `d` parentheses to left. Return true if string can be balanced.

**Examples.**
- Input: `((2))` → False

- Input: `((((2))` → True

- Input: `(()1(1))` → True

---

### 71) Restaurant Waitlist DS (Phone Screen, L4)

Design DS:

- Add customer group (by size).

- Remove arbitrary group.

- Given tableSize, return **first arrived group ≤ tableSize**.

Requires efficient queue+map design.

---

### 72) Palindrome by Splitting Two Strings (SDE2)

Strings A, B. Check if cutting both at some index → palindrome.

Follow-up: allow different split points → find **longest palindrome**.

**LC:** ✅ **LC 1616. Split Two Strings to Make Palindrome**.

---

### 73) Valid Playing Card Set (SWE2 Onsite)

Check if cards form:

1. Same rank, any suit, ≥3 cards.

2. Same suit, consecutive ranks, ≥3 cards.

Return boolean.

---

### 74) Sparse Bit Array (Onsite L3, Bangalore)

Sparse array with query(L,R) → 1 if a 1 exists in [L,R]. Find all positions of 1s with minimal queries.

Candidate: binary search + divide & conquer.

---

### 75) Theater Booking Duplicate Ticket Fix (SE-3)

Each customer accidentally got 2 tickets. Check if possible to **cancel 1 ticket each** to fix, else return “No”.

---

### 76) Tree Inorder Position Good/Bad (Google L3)

Given binary tree + nums[] list. If nums order follows inorder traversal → “good”, else “bad”.

---

### 77) NPCI OA (non-Google, but added for practice)

1. Reduce n to 1 using ops: (n-1) or (n/x). Use DP.
2. Longest increasing consecutive path in grid. DFS+DP.

---

### 78) Top N Most Recently Searched Items (Phone Screen SWE3)

Design DS: like **LRU Cache**, but for most recently searched items. Return Top N.

**Similar LC:** ✅ **LC 146. LRU Cache**.

---

### 79) Max Score from Word Combinations with Overlap (L5)

Given `words[]` with `score[]`, limit L. Can overlap words to form new words, accumulate scores. Maximize score under limit.

---

### 80) Equal-Area Line Splitting Rectangles (Onsite, Apr 2024)

Given rectangles [(x1,y1,x2,y2)], find vertical line dividing area equally. Binary search on x.

---

### 81) Song Shuffler with K Constraint (Onsite, 2022)

Design shuffler: next song random, but not in last K turns. Ensure equal probability among valid songs.

**LC Similar:** ✅ **LC 384. Shuffle an Array** (but with rolling exclusion).

---

### 82) Longest Subsequence with Diff = 1 (Coding R3, Jul 2024)

Variation of LIS with diff=1. Example: [2,3,1,4,3,5,6] → length=5 ([2,3,4,5,6]).

Candidate did recursion+DP.

---

### 83) Job Scheduling with Limited CPUs (Screening, 2021)

Jobs = {start, duration, cpu}. MaxCpus given. Return true if schedulable.

**Similar LC:** ✅ **LC 253. Meeting Rooms II** (extended with CPU count).

---

### 84) Max Ancestor Value for Leaves (Virtual Onsite, 2022)

Given n-ary tree. For each leaf, find max of ancestors + itself.

---

### 85) Recursively Delete Leaves in Multi-Tree (Onsite, 2022)

Remove leaves recursively until only root remains. Must do O(n).

---

### 86) Google 2024 Onsite Process (Nov 2024 – Jan 2025)

Round 1: Find node in graph to act as root of tree.

Round 2: Check if tree levels alternate colors.

Round 3: Rank n players given match outcomes (graph ordering).

Round 4: Allocate rooms to interns to maximize satisfied interns.

Round 5: PCB board with n components → max touched by one straight line.

---