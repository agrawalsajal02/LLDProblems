# File System Extensibility

## 1. Search by name
Current problem mein search out of scope hai.

If add karna ho:
- maintain `Map<String, List<FileSystemEntry>>`
- create/delete/rename/move pe index update karo

Already included helper idea:
- `NameIndexFileSystem.java`

## 2. Thread safety
Current implementation single-threaded hai.

Production direction:
- coarse-grained lock on `FileSystem`
or
- fine-grained folder locks

Important:
- `move()` mein source aur destination dono folders touch hote hain
- isliye lock ordering chahiye to avoid deadlock

Memory line:

`Single lock = simple`

`Per-folder lock = faster but tricky`

## 3. Non-empty folder delete policy
Current code non-empty folder delete allow karta hai.

If interviewer wants stricter behavior:
- before delete
- if entry is folder and children not empty
- throw exception

## 4. Relative paths
Currently:
- only absolute paths

If extend karna ho:
- current working directory maintain karo
- support `.` and `..`

## 5. Permissions and metadata
Can add fields:
- owner
- permissions
- createdAt
- modifiedAt

Best way:
- `FileSystemEntry` mein shared metadata add karo

## 6. Symbolic links
Current tree pure parent-child structure hai.

Symlink add karna ho:
- new entry type create karo
- target path store karo
- path resolution me link expansion ka logic add karo

But:
- cycle handling much harder ho jayegi

## 7. Persistent backing store
Current system pure RAM mein hai.

Future:
- snapshot root subtree to disk
- journal operations
- recover tree on startup

## 8. Search by prefix
If frequent prefix search chahiye:
- trie use kar sakte ho

## Easy memory line

`Base solve karo: tree + parent + map + path resolver`

`Extend bolo: search, locks, metadata, symlink, persistence`
