# File System LLD - Memory Guide

## 1) Core Objects
- `FileSystem`: public API + path resolution
- `Folder`: children map + parent link
- `File`: content + parent link
- `FileSystemEntry`: shared name/parent/path logic

## 2) Core Flows
Create file:
1. Resolve parent path
2. Name collision check
3. Add File to parent

Create folder:
1. Resolve parent path
2. Name collision check
3. Add Folder to parent

Delete:
1. Resolve parent
2. Remove child by name

Move:
1. Resolve src parent + entry
2. Resolve dest parent
3. Cycle check (folder into itself)
4. Remove from src, add to dest

Rename:
1. Remove from parent map
2. Update name
3. Re-add to parent map

## 3) Key Rules
- Absolute paths only
- Root cannot be deleted/moved/renamed
- Parent must exist
- Names must be unique per folder

## 4) Minimal API
- `createFile(path, content)`
- `createFolder(path)`
- `delete(path)`
- `list(path)`
- `get(path)`
- `rename(path, newName)`
- `move(src, dest)`
