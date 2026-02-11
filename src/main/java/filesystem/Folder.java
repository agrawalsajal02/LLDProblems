package filesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Folder extends FileSystemEntry {
    private final Map<String, FileSystemEntry> children;

    public Folder(String name) {
        super(name);
        this.children = new HashMap<>();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public FileSystemEntry getChild(String name) {
        return children.get(name);
    }

    public boolean addChild(FileSystemEntry entry) {
        if (entry == null) {
            return false;
        }
        if (children.containsKey(entry.getName())) {
            return false;
        }
        children.put(entry.getName(), entry);
        entry.setParent(this);
        return true;
    }

    public FileSystemEntry removeChild(String name) {
        FileSystemEntry removed = children.remove(name);
        if (removed != null) {
            removed.setParent(null);
        }
        return removed;
    }

    public List<FileSystemEntry> getChildren() {
        Collection<FileSystemEntry> values = children.values();
        return new ArrayList<>(values);
    }
}
