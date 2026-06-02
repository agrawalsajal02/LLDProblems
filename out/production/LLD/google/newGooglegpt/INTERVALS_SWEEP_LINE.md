# Intervals, Sweep Line, Difference Array

[Back to README](./README.md)

## Intervals - Overlap / Allocation

### 129. Overlap Intervals and Minimum Cars for Bookings

**Level / source tag:** Phone Screen

_Links:_ [LC 253 Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

**Problem.**
1. Given two intervals, check whether they overlap.
2. Given many booking intervals, assign the minimum number of cars/resources to cover all bookings.

**Approach.**
- Overlap if `max(s1,s2) < min(e1,e2)` for half-open intervals; adjust for inclusive rules.
- Minimum cars: sort intervals by start and use min-heap of end times, or sweep line.

## Intervals - Timeline / Active Counts

### 34. Active Users per Time `[0,N)`

**Level / source tag:** Onsite set

_Links:_ [LC 1109 Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/), [LC 253 Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

**Problem.** Given sessions `[start,end]` inclusive and `N`, compute for every time `t in [0,N)` the number of active users.

**Input / Output.**
- Input: `int[][] sessions`, `int N`
- Output: `int[] counts` length `N`

**Example.**
- `[(0,3),(1,4)], N=7 -> counts: t0=1, t1=2, t2=2, t3=2, t4=1`

**Approach.**
- Difference array: `diff[start]++`, `diff[end + 1]--` if inside bounds.

### 46. On-call Rotation Timeline

**Level / source tag:** Android SDE3 Phone

_Links:_ [LC 352 Data Stream as Disjoint Intervals](https://leetcode.com/problems/data-stream-as-disjoint-intervals/), [LC 986 Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)

**Problem.** Given `(name, start, end)` intervals, output non-overlapping timeline segments with the list of on-call people active in each segment.

**Input / Output.**
- Input: `List<Interval{name,s,e}>`
- Output: `List<Segment{start,end,names[]}>`

**Example from notes.**

```text
1 5 Abby
5 6 Abby, Ben
6 7 Abby, Ben, Carla
7 10 Abby, Carla
10 12 Carla
15 17 David
```

**Approach.**
- Sweep events by time.
- Add/remove names.
- Emit segment between consecutive event times when active set is non-empty.

## Intervals - Free Time

### 50. Common Free Time of All Persons

**Level / source tag:** Unspecified

_Links:_ [LC 759 Employee Free Time](https://leetcode.com/problems/employee-free-time/)

**Problem.** Given busy blocks `{personId,start,end}` and `totalTime`, return intervals where all persons are free.

**Input / Output.**
- Input: `List<Block> blocks`, `int totalTime`
- Output: `List<int[]> intervals`

**Approach.**
- Merge all busy intervals regardless of person.
- Complement inside `[0,totalTime]`.

## Intervals - Resource Capacity

### 83. Car Pooling

**Level / source tag:** Q88

_Links:_ [LC 1094 Car Pooling](https://leetcode.com/problems/car-pooling/)

**Problem.** Given `capacity` and trips `[numPassengers, from, to]`, check whether all trips can be served.

**Constraints.**
- `1 <= trips.length <= 1000`
- `1 <= numPassengers <= 100`
- `0 <= from < to <= 1000`
- `1 <= capacity <= 10^5`

**Examples.**
- `trips = [[2,1,5],[3,3,7]], capacity = 4 -> false`
- `trips = [[2,1,5],[3,3,7]], capacity = 5 -> true`

**Approach.**
- Difference array over stops or sweep line events.

### 89. Job Scheduling with Max CPUs Constraint

**Level / source tag:** Screening 2021 / Newly added

_Links:_ [Related LC 253 Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

**Problem.** Each job has `{start, duration, cpuNeed}` and there is a total `maxCpu` capacity. Return true if schedulable without exceeding capacity.

**Approach.**
- Sweep events:
  - at start add `cpuNeed`
  - at end subtract `cpuNeed`
- If active CPU exceeds max at any time, false.
