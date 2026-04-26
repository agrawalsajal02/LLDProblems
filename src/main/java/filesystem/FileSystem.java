package filesystem;

import java.util.List;
import java.util.Comparator;

public class FileSystem {
    private final Folder root;

    public FileSystem() {
        this.root = new Folder("/");
    }

    public File createFile(String path, String content) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot create file at root");
        }
        validateCreatablePath(path);
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
        validateCreatablePath(path);
        Folder parent = resolveParent(path);
        String name = extractName(path);

        if (parent.hasChild(name)) {
            throw new AlreadyExistsException("Entry already exists: " + name);
        }

        Folder folder = new Folder(name);
        parent.addChild(folder);
        return folder;
    }

    public FileSystemNode get(String path) {
        return resolvePath(path);
    }

    public List<FileSystemNode> list(String path) {
        FileSystemNode entry = resolvePath(path);
        if (!entry.isDirectory()) {
            throw new NotADirectoryException("Not a directory: " + path);
        }
        List<FileSystemNode> children = ((Folder) entry).getChildren();
        children.sort(Comparator.comparing(FileSystemNode::getName));
        return children;
    }

    public void delete(String path) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot delete root");
        }
        validateAbsolutePath(path);
        Folder parent = resolveParent(path);
        String name = extractName(path);
        FileSystemNode removed = parent.removeChild(name);
        if (removed == null) {
            throw new NotFoundException("Entry not found: " + path);
        }
    }

    public void rename(String path, String newName) {
        if ("/".equals(path)) {
            throw new InvalidPathException("Cannot rename root");
        }
        validateAbsolutePath(path);
        if (newName == null || newName.isEmpty() || newName.contains("/")) {
            throw new InvalidPathException("Invalid name");
        }

        Folder parent = resolveParent(path);
        String oldName = extractName(path);

        if (oldName.equals(newName)) {
            return;
        }

        if (!parent.hasChild(oldName)) {
            throw new NotFoundException("Entry not found: " + path);
        }
        if (parent.hasChild(newName)) {
            throw new AlreadyExistsException("Entry already exists: " + newName);
        }

        // Map key oldName hai, isliye remove + rename + add karna zaroori hai.
        FileSystemNode entry = parent.removeChild(oldName);
        entry.setName(newName);
        parent.addChild(entry);
    }

    public void move(String srcPath, String destPath) {
        if ("/".equals(srcPath)) {
            throw new InvalidPathException("Cannot move root");
        }
        validateAbsolutePath(srcPath);
        validateCreatablePath(destPath);

        Folder srcParent = resolveParent(srcPath);
        String srcName = extractName(srcPath);
        FileSystemNode entry = srcParent.getChild(srcName);
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

    private FileSystemNode resolvePath(String path) {
        validateAbsolutePath(path);
        if ("/".equals(path)) {
            return root;
        }

        // Absolute path ko parts me tod ke root se traverse karte hain.
        String[] parts = path.substring(1).split("/");
        FileSystemNode current = root;

        for (String part : parts) {
            if (part.isEmpty()) {
                throw new InvalidPathException("Invalid path: consecutive slashes");
            }
            if (!current.isDirectory()) {
                throw new NotADirectoryException("Not a directory in path: " + part);
            }
            Folder folder = (Folder) current;
            FileSystemNode child = folder.getChild(part);
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
        validateAbsolutePath(path);
        int lastSlash = path.lastIndexOf('/');
        String parentPath = (lastSlash == 0) ? "/" : path.substring(0, lastSlash);
        // Parent ko resolve karke ensure karte hain ki wo folder hi ho.
        FileSystemNode parent = resolvePath(parentPath);
        if (!parent.isDirectory()) {
            throw new NotADirectoryException("Parent is not a directory: " + parentPath);
        }
        return (Folder) parent;
    }

    private String extractName(String path) {
        int lastSlash = path.lastIndexOf('/');
        return path.substring(lastSlash + 1);
    }

    private void validateCreatablePath(String path) {
        validateAbsolutePath(path);
        String name = extractName(path);
        if (name.isEmpty()) {
            throw new InvalidPathException("Path must not end with /");
        }
    }

    private void validateAbsolutePath(String path) {
        if (path == null || path.isEmpty()) {
            throw new InvalidPathException("Invalid path");
        }
        if (!path.startsWith("/")) {
            throw new InvalidPathException("Path must be absolute");
        }
        if (path.length() > 1 && path.endsWith("/")) {
            throw new InvalidPathException("Path must not end with /");
        }
        if (path.contains("//")) {
            throw new InvalidPathException("Invalid path: consecutive slashes");
        }
    }
}
