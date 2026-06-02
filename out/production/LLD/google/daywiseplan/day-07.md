# Day 7: Focused 4-Question Set

**Date:** Monday, June 1, 2026

**Focus:** String search and substitution: recursive expansion, binary-search prefix, duplicate substring.

## Checklist

- [ ] Q33. Percent Variable Substitution with Nesting and Escaping (Strings, Parsing, Grammar, Substrings / Strings - Variables / Substitution)
- [ ] Q56. Variable Resolution with `#var#` References (Strings, Parsing, Grammar, Substrings / Strings - Variables / Substitution)
- [ ] Q54. Count Words with Prefix in Sorted Array (Strings, Parsing, Grammar, Substrings / Strings - Prefix / Binary Search / Trie)
- [ ] Q90. Max Ancestor Value for Each Leaf (Trees, Tries, Filesystem, Hierarchies / Trees - DFS / Leaves / Ancestors)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q33. Percent Variable Substitution with Nesting and Escaping

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Variables / Substitution

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** Onsite / L4

_Links:_ [2026 percent mapping post](https://leetcode.com/discuss/post/8218498/google-l4-in-person-interview-by-anonymo-eqbq/)

**Problem.** Given a map of variables, replace `%VAR%` in a string with values recursively. `%%` becomes a single literal `%`. Detect cycles.

**Input / Output.**
- Input: `String s`, `Map<String,String> env`
- Output: expanded string.

**Examples from posts.**
- `X -> "%y%/home"` requires recursive expansion.
- If a key is absent, clarify whether to return error, `-1`, or leave unchanged. 2026 post interviewer suggested returning an error.

**Approach.**
- Parser scans `%...%` tokens.
- DFS expand variable values with memoization.
- Use recursion stack set for cycle detection.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q56. Variable Resolution with `#var#` References

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Variables / Substitution

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** Google Onsite L4 Jul 2024

_Links:_ not provided in pasted notes.

**Problem.** Given a variable map where values may reference other vars via `#var#`, expand an input string fully.

**Example.**
- `b -> #a#src`
- `a -> data#c#`
- `c -> base`
- Input `hello#b#`
- Output `hellodatabasesrc`

**Approach.**
- Same as percent substitution: parse references, DFS expand, memoize, detect cycles.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q54. Count Words with Prefix in Sorted Array

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Prefix / Binary Search / Trie

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** Phone Screen L4

_Links:_ [LC 208 Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/), [LC 14 Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)

**Problem.** Given sorted array of strings and a prefix, count how many strings start with that prefix.

**Input / Output.**
- Input: `String[] arr`, `String prefix`
- Output: `int count`

**Example.**

```text
arr=["bomb","book","g","gift","go","goal","goat","gum","xray","yellow","zebra"]
prefix="go" -> 3
```

**Expected approach.**
- Binary search lower bound for `prefix`.
- Binary search lower bound for the next lexicographic string after prefix.
- Complexity `O(log n * k)` where `k` is prefix/string compare cost.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q90. Max Ancestor Value for Each Leaf

**Topic:** Trees, Tries, Filesystem, Hierarchies

**Type:** Trees - DFS / Leaves / Ancestors

**Original topic file:** [TREES_TRIES_FILESYSTEM.md](../TREES_TRIES_FILESYSTEM.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
