package filesystem.extensibility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filesystem.FileSystemNode;
import filesystem.Folder;

public class NameIndexFileSystem {
    private final Map<String, List<FileSystemNode>> nameIndex;
    private final Folder root;

    public NameIndexFileSystem(Folder root, Map<String, List<FileSystemNode>> nameIndex) {
        this.root = root;
        this.nameIndex = new HashMap<>(nameIndex);
    }

    public List<FileSystemNode> searchByName(String name) {
        return nameIndex.getOrDefault(name, List.of());
    }

    public Folder getRoot() {
        return root;
    }
}
