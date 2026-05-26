# Math, Geometry, Bit Manipulation

[Back to README](./README.md)

## Math - Number Theory

### 67. Subsets with LCM Divisible by k

**Level / source tag:** Google Phone Screen

_Links:_ not provided in pasted notes.

**Problem.** Count subsets whose LCM is divisible by `k`.

**Input / Output.**
- Input: `int[] arr`, `int k`
- Output: `long count`

**Examples.**
- `arr = [1, 7, 3, 2, 5]`, `k = 7` -> `16`
- `arr = [2, 3, 1, 6, 1]`, `k = 6` -> `20`

**Approach.**
- Reduce each number to `gcd(num,k)` because only prime factors of `k` matter.
- DP over divisors of `k`, tracking current LCM gcd-state.

## Math - Probability / Counting

### 59. Combination Lock Count within Tolerance (Two Combos)

**Level / source tag:** Google Onsite L4/L5

_Links:_ not provided in pasted notes.

**Problem.** 3-digit lock, digits wrap around `[0..numOptions-1]`. Two valid combos: user and bypass. A trial opens if each digit is within `tolerance` circular distance of either combo. Count distinct combinations that open.

**Input / Output.**
- Input: `int[] user`, `int[] bypass`, `int tolerance`, `int numOptions`
- Output: `int count`

**Variants.**
- Google Phone Screen Apr 26, 2024: count distinct length-3 combinations from two arrays with tolerance and wrapping bounds. Follow-up: count overlaps mathematically with `O(2t)` space.

**Approach.**
- Count size of cube around user plus cube around bypass minus intersection.
- Per digit, circular intervals may wrap; compute intersection product.

## Geometry - Rectangles / Lines / Circles

### 52. Equal-Area Vertical Line Across Overlapping Rectangles

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

### 80. Rectangle Area (Union of Two)

**Level / source tag:** Unspecified

_Links:_ [LC 223 Rectangle Area](https://leetcode.com/problems/rectangle-area/)

**Problem.** Given two axis-aligned rectangles, return total area covered.

**Input / Output.**
- Input: rectangle coordinates.
- Output: total area.

**Approach.**
- `areaA + areaB - overlapArea`.

### 112. Maximum Rectangle Area from 2D Points

**Level / source tag:** 2026 SDE3 Onsite

_Links:_ [2026 SDE3 experience](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

**Problem.** Given a set of points `[x,y]`, find the maximum area rectangle that can be formed using those points.

**Notes.**
- Interview focused on identifying rectangles efficiently.

**Approach.**
- For axis-aligned rectangles: group by x or y, track pairs of y-values seen at previous x.
- For arbitrary rectangles: more complex, use diagonals with same midpoint and length.

### 125. Line from Origin Avoiding All Segments

**Level / source tag:** Google Interview Aug 2024

_Links:_ not provided in pasted notes.

**Problem.** Given array of line segments, determine whether a line from origin `(0,0)` can be drawn that avoids all segments.

**Follow-up.**
- Line through arbitrary point `(x,y)`.

**Approach.**
- Convert each segment to blocked angular interval as seen from origin/point.
- Check whether union of blocked angle intervals covers the full circle.

## Bit Manipulation

### 51. Flip 1-bit-per-pixel Image Horizontally In-Place

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
