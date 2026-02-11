package filesystem;

import java.util.List;

public class FileSystem {
    private final Folder root;

    public FileSystem() {
        this.root = new Folder("/");
    }

    public File createFile(String path, String content) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot create file at root");
        }
        Folder parent = resolveParent(path);
        String name = extractName(path);

        if (parent.hasChild(name)) {
            throw new AlreadyExistsException("Entry already exists: " + name);
        }

        File file = new File(name, content);
        parent.addChild(file);
        return file;
    }

    public Folder createFolder(String path) {
        if ("/".equals(path)) {
            throw new AlreadyExistsException("Root already exists");
        }
        Folder parent = resolveParent(path);
        String name = extractName(path);

        if (parent.hasChild(name)) {
            throw new AlreadyExistsException("Entry already exists: " + name);
        }

        Folder folder = new Folder(name);
        parent.addChild(folder);
        return folder;
    }

    public FileSystemEntry get(String path) {
        return resolvePath(path);
    }

    public List<FileSystemEntry> list(String path) {
        FileSystemEntry entry = resolvePath(path);
        if (!entry.isDirectory()) {
            throw new NotADirectoryException("Not a directory: " + path);
        }
        return ((Folder) entry).getChildren();
    }

    public void delete(String path) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot delete root");
        }
        Folder parent = resolveParent(path);
        String name = extractName(path);
        FileSystemEntry removed = parent.removeChild(name);
        if (removed == null) {
            throw new NotFoundException("Entry not found: " + path);
        }
    }

    public void rename(String path, String newName) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot rename root");
        }
        if (newName == null || newName.isEmpty() || newName.contains("/")) {
            throw new InvalidPathException("Invalid name");
        }

        Folder parent = resolveParent(path);
        String oldName = extractName(path);

        if (!parent.hasChild(oldName)) {
            throw new NotFoundException("Entry not found: " + path);
        }
        if (parent.hasChild(newName)) {
            throw new AlreadyExistsException("Entry already exists: " + newName);
        }

        // Map key oldName hai, isliye remove + rename + add karna zaroori hai.
        FileSystemEntry entry = parent.removeChild(oldName);
        entry.setName(newName);
        parent.addChild(entry);
    }

    public void move(String srcPath, String destPath) {
        if ("/".equals(srcPath)) {
            throw new InvalidPathException("Cannot move root");
        }

        Folder srcParent = resolveParent(srcPath);
        String srcName = extractName(srcPath);
        FileSystemEntry entry = srcParent.getChild(srcName);
        if (entry == null) {
            throw new NotFoundException("Source not found: " + srcPath);
        }

        Folder destParent = resolveParent(destPath);
        String destName = extractName(destPath);

        if (destParent.hasChild(destName)) {
            throw new AlreadyExistsException("Destination already exists: " + destPath);
        }

        if (entry.isDirectory()) {
            // Cycle avoid: parent chain me agar entry mil gaya, to move invalid hai.
            Folder current = destParent;
            while (current != null) {
                if (current == entry) {
                    throw new InvalidPathException("Cannot move folder into itself");
                }
                current = current.getParent();
            }
        }

        srcParent.removeChild(srcName);
        entry.setName(destName);
        destParent.addChild(entry);
    }

    private FileSystemEntry resolvePath(String path) {
        if (path == null || path.isEmpty()) {
            throw new InvalidPathException("Invalid path");
        }
        if (!path.startsWith("/")) {
            throw new InvalidPathException("Path must be absolute");
        }
        if ("/".equals(path)) {
            return root;
        }

        // Absolute path ko parts me tod ke root se traverse karte hain.
        String[] parts = path.substring(1).split("/");
        FileSystemEntry current = root;

        for (String part : parts) {
            if (part.isEmpty()) {
                throw new InvalidPathException("Invalid path: consecutive slashes");
            }
            if (!current.isDirectory()) {
                throw new NotADirectoryException("Not a directory in path: " + part);
            }
            Folder folder = (Folder) current;
            FileSystemEntry child = folder.getChild(part);
            if (child == null) {
                throw new NotFoundException("Path not found: " + path);
            }
            current = child;
        }

        return current;
    }

    private Folder resolveParent(String path) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Root has no parent");
        }
        int lastSlash = path.lastIndexOf('/');
        String parentPath = (lastSlash == 0) ? "/" : path.substring(0, lastSlash);
        // Parent ko resolve karke ensure karte hain ki wo folder hi ho.
        FileSystemEntry parent = resolvePath(parentPath);
        if (!parent.isDirectory()) {
            throw new NotADirectoryException("Parent is not a directory: " + parentPath);
        }
        return (Folder) parent;
    }

    private String extractName(String path) {
        int lastSlash = path.lastIndexOf('/');
        return path.substring(lastSlash + 1);
    }
}
