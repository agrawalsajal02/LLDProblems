# Trees, Tries, Filesystem, Hierarchies

[Back to README](./README.md)

## Trees - DFS / Leaves / Ancestors

### 30. Remove Leaves in Rounds / Repeated Leaf Removal

**Level / source tag:** Onsite

_Links:_ [LC 366 Find Leaves of Binary Tree](https://leetcode.com/problems/find-leaves-of-binary-tree/)

**Problem.** For a rooted tree, remove all current leaves in rounds. Newly formed leaves cannot be removed in the same round unless no other nodes remain. Output removal order by rounds or flat order.

**Input / Output.**
- Input: tree.
- Output: `List<List<Integer>>` per round or flat removal order.

**Examples from notes.**
Multi-tree:

```text
1
/ | 2 5 3
/   |
7 4  9
    |
    8
Output: [7,8,9,3,4,5,2,1]
```

**Related.**
- LC 366 gives leaves by height for binary tree.

### 90. Max Ancestor Value for Each Leaf

**Level / source tag:** Virtual Onsite 2022

_Links:_ [Related LC 1026](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/)

**Problem.** Binary tree or n-ary tree. For each leaf, report maximum value among ancestors and itself.

**Example.**

```text
    4
   /   5   3
 /   / 1   2   6

Output: {1:5, 2:4, 6:6}
```

**Approach.**
- DFS carrying `maxSoFar`.

## Trees - Org / Manager

### 40. Managers with Salary Less Than Average of All Reports

**Level / source tag:** Onsite set

_Links:_ not provided in pasted notes.

**Problem.** Given org edges and salaries, count managers whose salary is less than the average salary of their direct and indirect reports.

**Input / Output.**
- Input: `Map<String,List<String>> reports`, `Map<String,Integer> salary`
- Output: `int count`

**Approach.**
- Post-order DFS.
- For each manager, collect descendant salary sum and count.
- Compare manager salary against `sum / count`.

### 71. Org Relations: `manager`, `peer`, `is_manager`

**Level / source tag:** Google Onsite Final

_Links:_ not provided in pasted notes.

**Problem.** Support online queries:
- `manager a b`: `a` is manager of `b`.
- `peer a b`: `a` and `b` share the same manager.
- `is_manager a b`: return whether `a` manages `b` directly or indirectly.

**Approach.**
- Clarify whether updates can contradict previous info.
- DSU for peers plus parent pointers for manager tree.
- For frequent ancestor checks, maintain Euler/timestamps if tree mostly static; online dynamic version is harder.

## Trees - Rope / Composite

### 60. Rope-like Substring from Internal Node

**Level / source tag:** Google Onsite L5 India

_Links:_ not provided in pasted notes.

**Problem.** Tree/composite structure: internal nodes have `len`, leaves have text. Given an internal node and `(start,length)` within its concatenation, return the substring.

**Input / Output.**
- Input: rope-like structure, index, length.
- Output: `String`.

**Approach.**
- Recursively descend using left child lengths.
- Collect only overlapping leaf ranges.

## Trees - Trie / Filesystem

### 8. Reduce Selected File Paths to Minimal Set

**Level / source tag:** L3 Onsite

_Links:_ [LC 1233 Remove Sub-Folders from the Filesystem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/)

**Problem.** Given all files and a selected subset, compress selection: if all items in a directory are selected, replace them by the directory path, recursively.

**Input / Output.**
- Input: `List<String> allFiles`, `List<String> selected`
- Output: `List<String> minimalSelection`

**Constraints.**
- Paths use `/`; no symlinks.
- Up to `10^5` files.

**Example from notes.**
- All: `/a/b/x.txt`, `/a/b/p.txt`, `/a/c`, `/a/d/y.txt`, `/a/d/z.txt`
- Selected: `/a/d/y.txt`, `/a/d/z.txt`, `/a/b/p.txt`
- Output: `/a/d`, `/a/b/p.txt`

**Approach.**
- Trie over path parts.
- Mark selected leaves.
- Post-order: if all children selected/compressed, return directory; else return selected descendants.

### 132. File System Entity Size by ID

**Level / source tag:** Google Phone Screen

_Links:_ not provided in pasted notes.

**Problem.** Given file system map `EntityID -> Entity{type,name,children,size}`, compute total size for given entity. Directories sum children recursively.

**Follow-ups.**
- Static data storage.
- GB-scale data.
- Recursive directories/cycles.

**Approach.**
- DFS with memoization.
- Detect cycles defensively if malformed.

## Trees - Binary Tree

### 98. Tree Inorder Position Good/Bad

**Level / source tag:** Google L3

_Links:_ not provided in pasted notes.

**Problem.** Given a binary tree and `nums[]`, return “good” if `nums` follows the inorder traversal order of the tree, otherwise “bad”.

**Approach.**
- Iterative inorder traversal comparing with `nums`.
