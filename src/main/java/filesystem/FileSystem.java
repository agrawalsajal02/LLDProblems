package filesystem;

import java.util.List;
import java.util.Comparator;

public class FileSystem {
    private final Folder root;
    private Folder currentDirectory;

    public FileSystem() {
        this.root = new Folder("/");
        this.currentDirectory = root;
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


//        if (entry.isDirectory()) {
//            if (destPath.equals(srcPath) || destPath.startsWith(srcPath + "/")) {
//                throw new InvalidPathException("Cannot move folder into itself or its descendant!");
//            }
//        }

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

    // return the end node (file or folder)
    private FileSystemNode resolvePath(String path) {
        //only root is allowed to end as /. All other valid paths should end with an actual entry name, which can be a folder name or a file name.
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

            // verify if the previous current is not file ///home/file.txt/docs
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
        //For /home, lastIndexOf('/') is 0.
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

    boolean mkdir(String dirname) {
        if (dirname == null || dirname.isEmpty() || dirname.contains("/") || dirname.contains("*")) {
            return false;
        }

        if (currentDirectory.hasChild(dirname)) {
            return false;
        }

        Folder folder = new Folder(dirname);
        return currentDirectory.addChild(folder);
    }

    String pwd() {
        return currentDirectory.getPath();
    }

    private String[] normalize(String path) {
        if (path == null || path.isEmpty()) {
            return new String[0];
        }

        if ("/".equals(path)) {
            return new String[0];
        }

        String normalized = path.startsWith("/") ? path.substring(1) : path;
        return normalized.split("/");
    }

    boolean cd(String path) {
        Folder start = path.startsWith("/") ? root : currentDirectory;
        String[] parts = normalize(path);
        Folder result = resolveWithWildcard(start, parts, 0);
        if (result == null) return false;
        currentDirectory = result;
        return true;
    }

    Folder resolveWithWildcard(Folder curr, String[] parts, int i) {
        if (i == parts.length) return curr;

        String part = parts[i];

        if (part.equals(".") || part.isEmpty()) {
            return resolveWithWildcard(curr, parts, i + 1);
        }

        if (part.equals("..")) {
            Folder next = curr.getParent() == null ? curr : curr.getParent();
            return resolveWithWildcard(next, parts, i + 1);
        }

        if (part.equals("*")) {
            Folder ans = resolveWithWildcard(curr, parts, i + 1);
            if (ans != null) return ans;

            if (curr.getParent() != null) {
                ans = resolveWithWildcard(curr.getParent(), parts, i + 1);
                if (ans != null) return ans;
            }

            for (FileSystemNode child : curr.getChildren()) {
                if (child.isDirectory()) {
                    ans = resolveWithWildcard((Folder) child, parts, i + 1);
                    if (ans != null) return ans;
                }
            }
            return null;
        }

        FileSystemNode child = curr.getChild(part);
        if (child == null || !child.isDirectory()) return null;
        return resolveWithWildcard((Folder) child, parts, i + 1);
    }
}
