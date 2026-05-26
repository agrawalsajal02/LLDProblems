# Google DSA Prep: Strings & Parsing (Categorized)

This document contains all Google-asked String, Stack-based parsing, and Context-Free Grammar (CFG) problems. They are ordered from bracket validations to full recursive-descent compiler engines (including custom equation parsers and function evaluators), and complex substring/subsequence optimizations.

---

## 1. Stacks, Brackets & String Simplifications

### ## 3) Decode String with k[encoded] (nested)
- **LeetCode Equivalent**: [LC 394. Decode String](https://leetcode.com/problems/decode-string/)
- **Problem Statement**:
  Given an encoded string, return its decoded string. The encoding rule is: `k[encoded_string]`, where the `encoded_string` inside the square brackets is repeated exactly `k` times. `k` is guaranteed to be a positive integer. You may assume that the input string is always valid; no extra spaces, square brackets are well-formed, etc. Furthermore, nesting of square brackets is fully allowed.
- **Input / Output**:
  - Input: `String s`
  - Output: decoded `String`
- **Constraints**:
  - `1 ≤ s.length() ≤ 10^5`
  - The output string length is guaranteed to fit in memory.
- **Examples**:
  - `s = "3[a2[c]]"` → `"accaccacc"` (nesting `a + cc` repeated 3 times)
  - `s = "3[a]2[bc]"` → `"aaabcbc"`
  - `s = "2[abc]3[cd]ef"` → `"abcabccdcdcdef"`
- **Java Starter**:
```java
class Solution {
    public String decodeString(String s) {
        
        return "";
    }
}
```

---

### ## 14) Valid Brackets with Unique Pairing Constraint
- **LeetCode Equivalent**: Similar to [LC 20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- **Problem Statement**:
  Given a string `s` containing only brackets `'('`, `')'`, `'['`, `']'`, `'{'`, and `'}'`. A bracket sequence is valid if standard nesting rules hold **and** each open bracket has **exactly one** valid, unambiguous closing bracket matching it (i.e. no overlapping pairing ambiguity).
- **Input / Output**:
  - Input: `String s`
  - Output: `boolean`
- **Constraints**:
  - `1 ≤ s.length() ≤ 10^5`
- **Examples**:
  - `"{[()()]}"` → `true`
  - `"[(])"` → `false`
  - `"{[(])}"` → `false` (overlap ambiguity)
- **Java Starter**:
```java
class Solution {
    public boolean isValidUnique(String s) {
        
        return false;
    }
}
```

---

### ## 79) Remove All Parentheses from Formula
- **LeetCode Equivalent**: Similar to [LC 224. Basic Calculator](https://leetcode.com/problems/basic-calculator/) sign propagation.
- **Problem Statement**:
  Given an algebraic expression containing variables (single letters `a-z`), addition `+`, subtraction `-`, and parentheses `()`. Simplify the expression by removing **all** parentheses and expanding the signs correctly. Return the simplified expression containing only letters and `+/-` without parentheses.
- **Input / Output**:
  - Input: `String s`
  - Output: `String` simplified expression
- **Constraints**:
  - `1 ≤ s.length() ≤ 1000`
- **Examples**:
  - `s = "a-(b+c)"` → `"a-b-c"` (distributing minus sign)
  - `s = "a-(a-b)"` → `"b"` (since `a - a + b` simplifies to `b`)
- **Java Starter**:
```java
class Solution {
    public String removeParentheses(String s) {
        
        return "";
    }
}
```

---

## 2. Context-Free Parsing & Evaluators (Recursive Descent)

### ## 11) Validate Equation Syntax (+, -, variables, parentheses, '=')
- **LeetCode Equivalent**: Custom CFG compiler validator. Similar to [LC 591. Tag Validator](https://leetcode.com/problems/tag-validator/)
- **Problem Statement**:
  Validate if a given equation string is syntactically correct.
  The Grammar Rules for the Equation language are:
  - **Equation** := `Expression '=' Expression`
  - **Expression** := `Term (('+' | '-') Term)*`
  - **Term** := `Variable | '(' Expression ')'`
  - **Variable** := `[a-z]`
  
  Valid operators are `+`, `-`, and brackets `()`. There must be exactly one `=` symbol separating two valid expressions. Note: unary minus is NOT allowed (e.g. `-x` is invalid, as the operator must be binary).
- **Input / Output**:
  - Input: `String expr`
  - Output: `boolean`
- **Constraints**:
  - `1 ≤ expr.length() ≤ 10^5`
- **Examples**:
  - `"a + x = b + (c + a)"` → `true`
  - `"a + x ="` → `false` (missing RHS expression)
  - `"a + -x = a + b"` → `false` (unary `-` is invalid in `-x`)
- **Complete Working Solution (Recursive Descent Parser)**:
  Below is the complete parser solution demonstrating how to parse this context-free grammar:
```java
class Solution {
    public boolean isValidEquation(String s) {
        Parser parser = new Parser(s);
        boolean ok = parser.parseEquation();
        return ok && parser.atEnd();
    }

    static class Parser {
        char[] ch;
        int i;

        Parser(String s) {
            ch = s.replaceAll("\\s+", "").toCharArray(); // strip spaces
            i = 0;
        }

        // Equation := Expression '=' Expression
        boolean parseEquation() {
            if (!parseExpression()) return false;
            if (!peek('=')) return false;
            i++; // consume '='
            return parseExpression();
        }

        // Expression := Term (('+' | '-') Term)*
        boolean parseExpression() {
            if (!parseTerm()) return false;
            while (peek('+') || peek('-')) {
                i++; // consume operator
                if (!parseTerm()) return false;
            }
            return true;
        }

        // Term := Variable | '(' Expression ')'
        boolean parseTerm() {
            if (peekVar()) {
                i++;
                return true;
            } else if (peek('(')) {
                i++; // consume '('
                if (!parseExpression()) return false;
                if (!peek(')')) return false;
                i++; // consume ')'
                return true;
            }
            return false;
        }

        boolean peek(char c) {
            return i < ch.length && ch[i] == c;
        }

        boolean peekVar() {
            return i < ch.length && (ch[i] >= 'a' && ch[i] <= 'z');
        }

        boolean atEnd() {
            return i == ch.length;
        }
    }
}
```

---

### ## 71) Function Evaluator: add, sub, mul, div, pow (floats)
- **LeetCode Equivalent**: Similar to [LC 224. Basic Calculator](https://leetcode.com/problems/basic-calculator/) and recursive descent math parsers.
- **Problem Statement**:
  Parse and evaluate a nested math functional string containing operations `add`, `sub`, `mul`, `div`, and `pow`. The numbers can be floating points and scientific notations (e.g. `2e3`). Assume division by zero or invalid overflow cases do not occur.
- **Input / Output**:
  - Input: `String expr`
  - Output: `double` evaluation result
- **Constraints**:
  - `1 ≤ expr.length() ≤ 10^5`
- **Examples**:
  - `eval("mul(2e3, sub(4,2))")` → `4000.0` (since $2000 * (4-2) = 4000$)
  - `eval("add(1.5, pow(2, 3))")` → `9.5` (since $1.5 + 2^3 = 9.5$)
- **Java Starter**:
```java
class Solution {
    public double eval(String s) {
        
        return 0.0;
    }
}
```

---

## 3. Substrings, Subsequences & Trie Operations

### ## 9) Shortest Missing Byte Sequence (a–f)
- **LeetCode Equivalent**: De Bruijn sequence / Trie substring searching.
- **Problem Statement**:
  Given a byte stream (represented as a string `s`) containing characters limited to the alphabet `{'a'..'f'}` (or general bytes `0..255`). Find the **shortest string** over this alphabet that **does not occur** as a substring of `s`. If multiple exist, return any.
- **Input / Output**:
  - Input: `String s`
  - Output: `String` representing the missing substring
- **Constraints**:
  - `1 ≤ s.length() ≤ 4 * 10^9` (stream can be large, optimize memory)
- **Examples**:
  - `s = "abcdefacbeddefd"` (with alphabet `'a'..'f'`)
    - Shortest missing sequence is length 2, e.g. `"aa"` (does not appear in `s`).
    - Output: `"aa"`
- **Java Starter**:
```java
class Solution {
    public String shortestMissingSubstring(String s) {
        
        return "";
    }
}
```

---

### ## 24) Generate Substrings to Reconstruct String (Unique Parts)
- **LeetCode Equivalent**: Similar to [LC 139. Word Break](https://leetcode.com/problems/word-break/) and greedy decompositions.
- **Problem Statement**:
  Given a string `S`. You need to decompose `S` into a list of substrings `L` such that concatenating all items in `L` in order reproduces `S`. To minimize redundancy, you must follow the rule: when scanning the string, **always choose the shortest new substring** that has not been added to `L` yet. Return the list `L`.
- **Input / Output**:
  - Input: `String s`
  - Output: `List<String>` unique parts list
- **Constraints**:
  - `1 ≤ s.length() ≤ 10^5`
- **Examples**:
  - `s = "GOOOOOOGLE"`
    - Step 1: `"G"` (new, add to list. Left: `"OOOOOOGLE"`)
    - Step 2: `"O"` (new, add to list. Left: `"OOOOOGLE"`)
    - Step 3: `"O"` is already in set. Let's try `"OO"` (new, add to list. Left: `"OOOOGLE"`)
    - Step 4: `"OO"` is in set. Let's try `"OOO"` (new, add to list. Left: `"GLE"`)
    - Step 5: `"G"` is in set. Try `"GL"` (new, add to list. Left: `"E"`)
    - Step 6: `"E"` (new, add to list)
    - Output: `["G", "O", "OO", "OOO", "GL", "E"]`
- **Java Starter**:
```java
class Solution {
    public java.util.List<String> decompose(String s) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 33) Percent-Variable Substitution (nested)
- **LeetCode Equivalent**: Graph-based String substitution/expansion with cycle detection.
- **Problem Statement**:
  Given a string template `s` and a map of variables `env` (name → value). Expand all variables inside `s` enclosed in percent signs `%VAR%` recursively (the values in the map can themselves contain references to other variables). A double percent `%%` represents an escaped single percent sign `%` and should not be replaced. If there is a circular dependency (infinite substitution loop), throw an exception or handle the cycle. *(Also cross-referenced as #56).*
- **Input / Output**:
  - Input: `String s`, `Map<String, String> env`
  - Output: expanded `String`
- **Constraints**:
  - Length of `s` and values `≤ 10^4`, no infinite cycles.
- **Java Starter**:
```java
class Solution {
    public String expand(String s, java.util.Map<String, String> env) {
        
        return "";
    }
}
```

---

### ## 61) Rope-like Substring from Internal Node
- **LeetCode Equivalent**: String Rope Data Structure traversal. Similar to [LC 385. Mini Parser](https://leetcode.com/problems/mini-parser/)
- **Problem Statement**:
  A Rope is a binary tree where leaf nodes contain string fragments, and internal nodes hold a `len` field representing the total length of the concatenated strings in their subtree. Given a pointer to an internal node of the Rope tree and a target interval defined by `(start, length)`. Extract and return the substring within that range.
- **Input / Output**:
  - Input: `Node node`, `int start`, `int length`
  - Output: `String`
- **Constraints**:
  - String fragments length can be large; optimize tree traversal without full concatenation.
- **Java Starter**:
```java
class Solution {
    static class Node {
        boolean leaf;
        int len;
        String text;
        Node left, right;
    }
    public String findSubstring(Node node, int start, int length) {
        
        return "";
    }
}
```

---

### ## 63) Subsequence in A allowing ≤1 change in B
- **LeetCode Equivalent**: Similar to [LC 392. Is Subsequence](https://leetcode.com/problems/is-subsequence/) with error tolerance.
- **Problem Statement**:
  Check if a string `B` is a subsequence of string `A` after changing **at most one** character in `B` (under the condition that you **cannot change the first character** of `B`). If a valid match exists, return the **starting index (1-based)** of the subsequence in `A`. If impossible, return `-1`.
- **Input / Output**:
  - Input: `String A`, `String B`
  - Output: `int` (1-based index in A, or -1 if impossible)
- **Constraints**:
  - `1 ≤ A.length() ≤ 10^5`, `1 ≤ B.length() ≤ 1000`
- **Java Starter**:
```java
class Solution {
    public int findSubsequenceWithOneChange(String A, String B) {
        
        return -1;
    }
}
```
