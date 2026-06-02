# Strings, Parsing, Grammar, Substrings

[Back to README](./README.md)

## Strings - Parsing / Grammar

### 11. Validate Equation Syntax (`+`, `-`, variables, parentheses, `=`)

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

### 69. Function Evaluator: `add`, `sub`, `mul`, `div`, `pow`

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Evaluate expressions like `mul(2e3, sub(4,2))`. Inputs are floating point. No overflow/divide-by-zero cases.

**Input / Output.**
- Input: `String expr`
- Output: `double`

**Example.**
- `mul(2e3, sub(4,2)) -> 4000`

**Approach.**
- Recursive descent parser:
  - parse function name
  - parse `(`
  - parse comma-separated arguments
  - parse `)`
  - parse scientific notation numbers.

### 100. Boolean Expression Satisfiable Library

**Level / source tag:** Extended round

_Links:_ not provided in pasted notes.

**Problem.** Design a library to solve a boolean expression and return whether it can ever evaluate to true.

**Examples.**
- `((a || b) && c)` can be true when `c` is true and at least one of `a,b` is true.
- `a && ~a` can never be true.

**Expectations from pasted experience.**
1. How would a math-oriented user invoke the library?
2. What should input look like?
3. What classes/APIs are needed?
4. Which data structures are used and why?
5. Code final evaluation logic assuming helpers for format/errors.
6. State time and space complexity.

**Approach.**
- Parse to AST.
- Brute force assignments `2^n` is acceptable as a baseline.
- Advanced: convert to SAT/CNF or use symbolic simplification.

## Strings - Stack / Decode

### 3. Decode String with `k[encoded]` (Nested)

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

## Strings - Variables / Substitution

### 33. Percent Variable Substitution with Nesting and Escaping

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

### 56. Variable Resolution with `#var#` References

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

## Strings - Subsequence / Substring

### 9. Shortest Missing Byte Sequence

**Level / source tag:** L3 Phone

_Links:_ [LC 1461 Check If a String Contains All Binary Codes](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/), [LC 1980 Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string/)

**Problem.** Given a byte stream limited initially to alphabet `{'a'..'f'}` and later generalized to bytes `0..255`, find the shortest string over the alphabet that does not occur as a substring.

**Input / Output.**
- Input: `String s` or byte array, alphabet size `Σ`.
- Output: any missing substring of minimum length.

**Constraints.**
- Pasted note mentions input up to `4GB` and memory considerations.

**Example.**
- `"abcdefacbeddefd" -> "aa"` because all single characters exist and `"aa"` is absent.

**Approach notes.**
- Test length `k = 1, 2, ...`.
- Mark observed substrings of length `k` via rolling hash or direct byte rolling index.
- Stop when not all `Σ^k` strings are present.
- For huge files, scan streaming and use bitsets when feasible.

### 24. Generate Substrings to Reconstruct String (Unique List)

**Level / source tag:** L3 Phone

_Links:_ not provided in pasted notes.

**Problem.** Given string `S`, generate list `L` of substrings such that concatenating in order reproduces `S`. If a substring is new, add it to `L`.

**Input / Output.**
- Input: `String s`
- Output: `List<String> parts`

**Constraints.**
- `1 <= |s| <= 1e5`

**Examples.**
- `"GOOOOOOGLE" -> ["G","O","OO","OOO","GL","E"]`
- `"GOOOOOOGLEG" -> ["G","O","OO","OOO","GL","E","G"]`

**Approach.**
- Greedy dictionary-building. At each position, extend while substring has been seen, then emit the shortest unseen token.

### 61. Subsequence in A Allowing At Most One Change in B (Not First Char)

**Level / source tag:** Google OA 2024

_Links:_ not provided in pasted notes.

**Problem.** Check if `B` is a subsequence of `A` after changing at most one character in `B`, excluding the first character. Return starting index (1-based) where it occurs, else `-1`.

**Input / Output.**
- Input: `String A`, `String B`
- Output: starting index 1-based or `-1`.

**Examples.**
- `A = "abcbc"`, `B = "cbe"` -> `3`
- `A = "lhs"`, `B = "rhs"` -> `-1`

**Approach.**
- Two-pointer with state whether mismatch used.
- If “starting index” means index of first matched char, preserve it during matching.

### 85. Longest Duplicate Substring

**Level / source tag:** Q90

_Links:_ [LC 1044 Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/)

**Problem.** Given string `s`, return the longest duplicated substring (appears at least twice). If none exists, return `""`.

**Constraints.**
- `2 <= s.length <= 3 * 10^4`
- Lowercase English letters.

**Examples.**
- `"banana" -> "ana"`
- `"abcd" -> ""`

**Approach.**
- Binary search length + rolling hash.
- Suffix array / suffix automaton alternatives.

### 88. Merge Two Screenshots by Overlapping Rows

**Level / source tag:** Q96

_Links:_ not provided in pasted notes.

**Problem.** Given two screenshots of size `h x w`, merge into one without overlapping repeated rows.

**Example.**

```text
Screen1:
[1 2 3]
[4 4 5]
[6 7 8]
[9 10 11]

Screen2:
[6 7 8]
[9 10 11]
[13 14 15]
[16 17 19]

Output:
[1 2 3]
[4 4 5]
[6 7 8]
[9 10 11]
[13 14 15]
[16 17 19]
```

**Approach.**
- Find max suffix of screen1 equal to prefix of screen2.
- KMP/rolling hash over rows for efficient overlap detection.

### 95. Palindrome by Splitting Two Strings

**Level / source tag:** SDE2

_Links:_ [LC 1616 Split Two Strings to Make Palindrome](https://leetcode.com/problems/split-two-strings-to-make-palindrome/)

**Problem.** Given strings `A` and `B`, check if cutting both at the same index and combining prefix of one with suffix of the other can form a palindrome.

**Follow-up.**
- Allow different split points; find longest palindrome.

**Approach.**
- Two-pointer compare cross strings, then validate remaining middle substring in either original string.

## Strings - Prefix / Binary Search / Trie

### 54. Count Words with Prefix in Sorted Array

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

## Strings - Parentheses / Brackets

### 14. Valid Brackets with Unique Pairing Constraint

**Level / source tag:** Onsite

_Links:_ [LC 20 Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

**Problem.** Given `s` containing `()[]{}`, validate normal bracket rules and also require that each open bracket has exactly one valid closing, with no ambiguity.

**Input / Output.**
- Input: `String s`
- Output: `boolean`

**Constraints.**
- `1 <= |s| <= 10^5`

**Examples.**
- `"{[()()]}" -> true`
- `"[(])" -> false`
- `"{[(])}" -> false`

**Base mapping.**
- Normal stack validation is LC 20.
- Clarify with interviewer what “unique pairing” means for nested alternatives; common implementation is still deterministic stack pairing.

### 75. Remove All Parentheses from Formula

**Level / source tag:** Google L3 / Phone Screening

_Links:_ not provided in pasted notes.

**Problem.** Given letters and `+`, `-`, `(`, `)`, remove parentheses by applying signs.

**Examples.**
- `a-(b+c) -> a-b-c`
- `a-(a-b) -> b` in pasted note. This second example implies simplification/cancellation of like terms, not only sign distribution.

**Input / Output.**
- Input: `String s`
- Output: simplified string with only `+/-` and letters, no parentheses.

**Approach.**
- Maintain sign stack for parentheses.
- If combining like terms, accumulate coefficient per variable and render nonzero terms.

### 94. Balance Parentheses with Deletions at Digit Index

**Level / source tag:** Phone Screen

_Links:_ not provided in pasted notes.

**Problem.** String contains parentheses and digits. At digit index `d`, must delete `d` parentheses to the left. Return true if the string can be balanced.

**Examples.**
- `((2)) -> false`
- `((((2)) -> true`
- `(()1(1)) -> true`

**Approach.**
- Interpret digits as deletion constraints.
- Track possible balance ranges after forced deletions; clarify whether each digit is an operation point or literal constraint.

## Strings - Serialization

### 68. Binary Tree to String Representation

**Level / source tag:** Google OA

_Links:_ [LC 606 Construct String from Binary Tree](https://leetcode.com/problems/construct-string-from-binary-tree/)

**Problem.** Given `Node(char val, left, right)`, return a preorder-like string representation. Exact format depends on prompt examples.

**Input / Output.**
- Input: tree root.
- Output: `String`.

**Approach.**
- Clarify null marker and parentheses format.
- Common LC variant: omit unnecessary empty parentheses except when needed to preserve structure.
