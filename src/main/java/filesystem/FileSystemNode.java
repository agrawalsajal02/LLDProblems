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

    // simpler
//    public String getPath() {
//        if (parent == null) {
//            return "";
//        }
//
//        String parentPath = parent.getPath();
//        return parentPath + "/" + name;
//    }

    public String getPath() {
        if (parent == null) {
            return name;
        }

        String parentPath = parent.getPath();

        //parentPath = parent.getPath() means first get the full path of the parent folder.
        // Then if that parent path is /, it means the current entry is directly under root. So for something like home, you want "/" + "home" = "/home".
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
