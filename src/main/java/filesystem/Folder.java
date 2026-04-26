package filesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Folder extends FileSystemNode {
    public Folder(String name) {
        super(name);
    }

    @Override
    boolean isFile() {
        return false;
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public boolean addChild(FileSystemNode entry) {
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

    public FileSystemNode removeChild(String name) {
        FileSystemNode removed = children.remove(name);
        if (removed != null) {
            removed.setParent(null);
        }
        return removed;
    }

    public List<FileSystemNode> getChildren() {
        Collection<FileSystemNode> values = children.values();
        return new ArrayList<>(values);
    }

    @Override
    public void display(int depth) {
        System.out.println("  ".repeat(Math.max(0, depth)) + "+ " + getName());
        for (FileSystemNode child : getChildren()) {
            child.display(depth + 1);
        }
    }
}
