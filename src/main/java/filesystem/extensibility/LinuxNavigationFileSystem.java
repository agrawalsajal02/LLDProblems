package filesystem.extensibility;

import filesystem.FileSystem;
import filesystem.FileSystemNode;
import filesystem.Folder;
import filesystem.InvalidPathException;
import filesystem.NotADirectoryException;
import filesystem.NotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class LinuxNavigationFileSystem {
    private final FileSystem delegate;
    private final Folder root;
    private Folder currentDirectory;

    public LinuxNavigationFileSystem() {
        this.delegate = new FileSystem();
        this.root = (Folder) delegate.get("/");
        this.currentDirectory = root;
    }

    public LinuxNavigationFileSystem(FileSystem delegate) {
        this.delegate = delegate;
        this.root = (Folder) delegate.get("/");
        this.currentDirectory = root;
    }

    public void mkdir(String dirname) {
        if (dirname == null || dirname.isEmpty() || dirname.contains("/") || dirname.contains("*")) {
            throw new InvalidPathException("mkdir only supports a direct directory name inside current directory");
        }

        String currentPath = currentDirectory.getPath();
        String fullPath = "/".equals(currentPath) ? currentPath + dirname : currentPath + "/" + dirname;
        delegate.createFolder(fullPath);
    }

    public String pwd() {
        return currentDirectory.getPath();
    }

    public void cd(String path) {
        Folder resolved = resolveDirectory(path);
        this.currentDirectory = resolved;
    }

    public FileSystem getDelegate() {
        return delegate;
    }

    public Folder getCurrentDirectory() {
        return currentDirectory;
    }

    private Folder resolveDirectory(String path) {
        if (path == null || path.isEmpty()) {
            throw new InvalidPathException("Path cannot be empty");
        }

        List<Folder> currentCandidates = new ArrayList<>();
        currentCandidates.add(path.startsWith("/") ? root : currentDirectory);

        if ("/".equals(path)) {
            return root;
        }

        String normalized = path.startsWith("/") ? path.substring(1) : path;
        if (normalized.isEmpty()) {
            return root;
        }

        String[] parts = normalized.split("/");
        for (String part : parts) {
            if (part.isEmpty()) {
                throw new InvalidPathException("Invalid path: consecutive slashes");
            }
            currentCandidates = advance(currentCandidates, part, path);
        }

        if (currentCandidates.isEmpty()) {
            throw new NotFoundException("Path not found: " + path);
        }
        if (currentCandidates.size() > 1) {
            throw new InvalidPathException("Ambiguous path due to wildcard: " + path);
        }
        return currentCandidates.get(0);
    }

    private List<Folder> advance(List<Folder> currentCandidates, String part, String originalPath) {
        Map<String, Folder> nextUnique = new LinkedHashMap<>();

        for (Folder folder : currentCandidates) {
            if (".".equals(part)) {
                nextUnique.put(folder.getPath(), folder);
                continue;
            }

            if ("..".equals(part)) {
                Folder parent = folder.getParent();
                nextUnique.put(parent == null ? folder.getPath() : parent.getPath(), parent == null ? folder : parent);
                continue;
            }

            if ("*".equals(part)) {
                // Requirement-specific wildcard:
                // * can match current directory, parent directory, or any child directory.
                nextUnique.put(folder.getPath(), folder);

                Folder parent = folder.getParent();
                if (parent != null) {
                    nextUnique.put(parent.getPath(), parent);
                }

                for (FileSystemNode child : folder.getChildren()) {
                    if (child.isDirectory()) {
                        Folder childFolder = (Folder) child;
                        nextUnique.put(childFolder.getPath(), childFolder);
                    }
                }
                continue;
            }

            FileSystemNode child = folder.getChild(part);
            if (child == null) {
                continue;
            }
            if (!child.isDirectory()) {
                throw new NotADirectoryException("Not a directory in path: " + originalPath);
            }
            Folder childFolder = (Folder) child;
            nextUnique.put(childFolder.getPath(), childFolder);
        }

        return new ArrayList<>(nextUnique.values());
    }
}
