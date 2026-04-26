package filesystem;

import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {
    // name of folder or file
    protected String name;
    protected Folder parent;
    public Map<String, FileSystemNode> children;

    public FileSystemNode(String name) {
        this.name = name;
        this.parent = null;
        this.children = new HashMap<>();
    }

    public void displayName() {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public String getPath() {
        if (parent == null) {
            return name;
        }

        String parentPath = parent.getPath();
        if ("/".equals(parentPath)) {
            return "/" + name;
        }
        return parentPath + "/" + name;
    }

    abstract boolean isFile();

    public boolean isDirectory() {
        return !isFile();
    }

    public abstract void display(int depth);
}
