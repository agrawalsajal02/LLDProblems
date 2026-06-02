# Day 1: Focused 4-Question Set

**Date:** Tuesday, May 26, 2026

**Focus:** Warm-up: parsing, simulation, filesystem compression, and core stack/string confidence.

## Checklist

- [ ] Q3. Decode String with `k[encoded]` (Nested) (Strings, Parsing, Grammar, Substrings / Strings - Stack / Decode)
- [ ] Q11. Validate Equation Syntax (`+`, `-`, variables, parentheses, `=`) (Strings, Parsing, Grammar, Substrings / Strings - Parsing / Grammar)
- [ ] Q1. Collatz Steps (Arrays, Greedy, Sorting, Two Pointers / Arrays - Simulation)
- [ ] Q8. Reduce Selected File Paths to Minimal Set (Trees, Tries, Filesystem, Hierarchies / Trees - Trie / Filesystem)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q3. Decode String with `k[encoded]` (Nested)

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Stack / Decode

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** L3 R2

_Links:_ [LC 394 Decode String](https://leetcode.com/problems/decode-string/)

**Problem.** Decode strings like `"3[a2[c]]" -> "accaccacc"`. `k` is a positive integer and nesting is allowed.

**Input / Output.**
- Input: `String s`
- Output: decoded `String`

**Constraints.**
- `1 <= |s| <= 10^5`

**Examples.**
- `"3[a]2[bc]" -> "aaabcbc"`
- `"3[a2[c]]" -> "accaccacc"`
- `"2[abc]3[cd]ef" -> "abcabccdcdcdef"`

**Notes.**
- Candidate stack approach; recursion vs stack discussed.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q11. Validate Equation Syntax (`+`, `-`, variables, parentheses, `=`)

**Topic:** Strings, Parsing, Grammar, Substrings

**Type:** Strings - Parsing / Grammar

**Original topic file:** [STRINGS_AND_PARSING.md](../STRINGS_AND_PARSING.md)

**Level / source tag:** Unspecified / pasted custom prompt

_Links:_ [LC 224 Basic Calculator](https://leetcode.com/problems/basic-calculator/), [LC 227 Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/), [LC 772 Basic Calculator III](https://leetcode.com/problems/basic-calculator-iii/)

**Problem.** Validate whether an equation is syntactically correct. Characters: letters `a-z`, operators `+` and `-`, parentheses, and exactly one `=`.

**Important conflict preserved from pasted notes.**
- The first pasted source says unary `-x` is invalid:
  - Valid: `a + x = b + (c + a)`
  - Invalid: `a + x =`
  - Invalid: `a + -x = a + b` because `-x` is unary.
- Later master sheet says unary minus is allowed:
  - `"a + -x = a + b" -> true`

Keep both variants in prep. In interview, clarify whether unary operators are allowed before coding.

**Grammar from prompt for no-unary variant.**

```text
Equation   := Expression '=' Expression
Expression := Term (('+' | '-') Term)*
Term       := Variable | '(' Expression ')'
Variable   := [a-z]
```

**Spaces.**
- Strip spaces before parsing, or skip them during tokenization.

**Test cases from prompt.**
- `"a + x = b + (c + a)" -> true`
- `"a + x =" -> false`
- `"a + -x = a + b"` -> false in no-unary variant, true in unary-allowed variant
- `"(a+b) = (c)" -> true`
- `"=a+b" -> false`

**Approach.**
- Recursive descent parser.
- Parse equation as expression, then `=`, then expression.
- At end, ensure all characters consumed.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q1. Collatz Steps

**Topic:** Arrays, Greedy, Sorting, Two Pointers

**Type:** Arrays - Simulation

**Original topic file:** [ARRAYS_GREEDY_TWO_POINTERS.md](../ARRAYS_GREEDY_TWO_POINTERS.md)

**Level / source tag:** L4 Screening

_Links:_ not provided in pasted notes.

**Problem.** Given `n > 0`, repeatedly apply: if `n` is even, `n = n / 2`; otherwise `n = 3*n + 1`. Count the number of steps until `n == 1`.

**Input / Output.**
- Input: `int n` / `long n`
- Output: number of steps to reach 1

**Constraints.**
- `1 <= n <= 10^12`; use 64-bit.
- Worst-case steps are not known in a simple closed form.

**Examples.**
- `n = 1 -> 0`
- `n = 6 -> 8`, because `6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1`

**Notes from pasted experience.**
- L4 screening in notes.
- Follow-up: make it modular / OOP.
- Mapping: no exact LeetCode; simulation + optional memoization.

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q8. Reduce Selected File Paths to Minimal Set

**Topic:** Trees, Tries, Filesystem, Hierarchies

**Type:** Trees - Trie / Filesystem

**Original topic file:** [TREES_TRIES_FILESYSTEM.md](../TREES_TRIES_FILESYSTEM.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
