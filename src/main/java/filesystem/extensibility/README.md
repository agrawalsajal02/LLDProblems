# File System Extensibility (Interview Notes)

## 1) Search by name
- Maintain a `Map<String, List<Entry>>` as index.
- Update index on create/delete/rename/move.

## 2) Thread-safety (idea)
- Coarse lock on FileSystem methods or
- Fine-grained lock on folders with consistent lock ordering for move.
