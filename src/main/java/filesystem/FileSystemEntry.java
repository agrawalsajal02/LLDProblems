package filesystem;

public abstract class FileSystemEntry {
    private String name;
    private Folder parent;

    protected FileSystemEntry(String name) {
        this.name = name;
        this.parent = null;
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
        // Root ka parent null hota hai, isliye wahi base path hai.
        if (parent == null) {
            return name;
        }

        String parentPath = parent.getPath();
        if ("/".equals(parentPath)) {
            return "/" + name;
        }
        return parentPath + "/" + name;
    }

    public abstract boolean isDirectory();
}
