# Linux-Style Navigation Extension

## Problem this extension solves
Round 3 style requirement:
- `mkdir(dirname)`
- `pwd()`
- `cd(path)`

And path rules:
- absolute path if it starts with `/`
- relative path otherwise
- path may contain:
- `.`
- `..`
- `*`

Goal:
- Linux-like navigation behavior add karna
- existing file system implementation ko break ya modify kiye bina

## Requirements covered
- create a directory inside current working directory using `mkdir(dirname)`
- return absolute path of current working directory using `pwd()`
- change current working directory using `cd(path)`
- support absolute path navigation
- support relative path navigation
- support current directory token `.`
- support parent directory token `..`
- support wildcard token `*`

## What is intentionally not added here
- file creation through Linux shell-style commands like `touch`
- file reads/writes through shell commands
- multiple current sessions/users
- permissions
- shell parsing

## Design approach
This extension uses a wrapper:

- `LinuxNavigationFileSystem`

It composes the already existing:
- `filesystem.FileSystem`

It reuses:
- `Folder`
- `FileSystemNode`
- parent pointers

And adds only one new runtime state:
- `currentDirectory`

## Why this approach is good
- existing file system code untouched rehta hai
- Round 3 navigation APIs cleanly add ho jaate hain
- path traversal logic separate rehta hai
- easier to explain in interview:
- "base tree alag hai, shell-style navigation layer alag hai"

## Supported operations

### 1. `mkdir(dirname)`
Behavior:
- current working directory ke andar new folder create karta hai
- only direct directory name accept karta hai

Examples:
- if current directory = `/home`
- `mkdir("user")` -> creates `/home/user`

Validation:
- empty name invalid
- `/` not allowed inside name
- `*` not allowed in mkdir name

### 2. `pwd()`
Behavior:
- current working directory ka absolute path return karta hai

Examples:
- `/`
- `/home`
- `/home/user/docs`

### 3. `cd(path)`
Behavior:
- current working directory change karta hai

Supports:
- absolute paths
- relative paths
- `.`
- `..`
- `*`

Examples:
- `cd("/home/user")`
- `cd("docs")`
- `cd(".")`
- `cd("..")`
- `cd("*")`

## Path rules

### Absolute path
If path starts with `/`
- traversal root se start hota hai

Example:
- `cd("/home/user")`

### Relative path
If path `/` se start nahi hota
- traversal current directory se start hota hai

Example:
- current = `/home`
- `cd("user/docs")` -> `/home/user/docs`

### `.`
Means current directory

Example:
- current = `/home/user`
- `cd(".")` -> stays `/home/user`

### `..`
Means parent directory

Example:
- current = `/home/user`
- `cd("..")` -> `/home`

At root:
- `cd("..")` stays at `/`

### `*`
Requirement-specific wildcard support:
- can match current directory
- can match parent directory
- can match any child directory

## Wildcard behavior in this implementation
Because `*` can match multiple folders, this extension uses deterministic behavior:

- if wildcard resolves to exactly one directory -> `cd` succeeds
- if wildcard resolves to zero directories -> `NotFoundException`
- if wildcard resolves to multiple directories -> `InvalidPathException`

Why?
- interview mein ambiguous navigation avoid karna easy hota hai
- predictable API behavior milta hai

## Internal flow

### `mkdir(dirname)`
1. validate direct name
2. get current directory path
3. build absolute path using current directory
4. delegate to base `FileSystem.createFolder(...)`

### `pwd()`
1. just return `currentDirectory.getPath()`

### `cd(path)`
1. decide start point:
- root for absolute path
- currentDirectory for relative path
2. split path into parts
3. advance folder-by-folder
4. finally update `currentDirectory`

## Classes

### `LinuxNavigationFileSystem`
Main wrapper class.

Responsibilities:
- stores `currentDirectory`
- exposes `mkdir`, `pwd`, `cd`
- resolves Linux-style paths

### `LinuxNavigationMain`
Demo runner.

## Example flow
Starting from root:

1. `mkdir("home")`
2. `cd("/home")`
3. `mkdir("user")`
4. `cd("user")`
5. `mkdir("docs")`
6. `pwd()` -> `/home/user`
7. `cd("..")` -> `/home`

## Interview explanation in simple Hinglish

`Base file system tree ko manage karta hai.`

`Ye extension current working directory ko manage karti hai.`

`mkdir current directory ke andar create karta hai.`

`pwd current pointer ka absolute path deta hai.`

`cd absolute ya relative path parse karke current pointer ko move karta hai.`

## Memory lines
- `Base file system tree sambhalta hai`
- `Extension current directory sambhalti hai`
- `cd bas current pointer ko move karta hai`
- `mkdir current folder ke andar child add karta hai`

## Files
- `LinuxNavigationFileSystem.java`
- `LinuxNavigationMain.java`
- `LINUX_NAVIGATION_README.md`
