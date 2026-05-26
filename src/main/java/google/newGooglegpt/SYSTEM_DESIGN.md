# System Design

[Back to README](./README.md)

## System Design - Storage

### 21. Versioned JSON Storage / Design Diff API

**Level / source tag:** Senior / System Design

_Links:_ not provided in pasted notes.

**Problem.** Design an API to store all versions of a JSON object efficiently using diffs. Provide functions to write a new version and fetch any version.

**API shape.**
- `put(JSONObject v)` or `put(String json)`
- `get(int version)`

**Constraints and discussion points.**
- Large objects.
- Minimize storage.
- Decide snapshot interval vs patch chain.
- Fetch latency should not require replaying too many diffs.
- Consider JSON Patch, structural sharing, compression, compaction, garbage collection, and conflict handling.

## System Design - Ads / Budget

### 124. Ad Budget Decision API

**Level / source tag:** System Design prep list

_Links:_ not provided in pasted notes.

**Problem.** Design an API to determine whether to show an ad by `AdId`. Each ad has a total budget that decreases each time it is shown. If budget is non-zero, ad can be shown. System must be highly available, scalable, and low latency. Consistency in budget updates can tolerate some error margin.

**Discussion points.**
- Counter storage and race conditions.
- Strong vs eventual consistency trade-off.
- Local budget shards / token buckets per serving node.
- Reconciliation and over-spend bounds.
- Monitoring and kill switches.
