package filesystem.practice;

import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode  {
    private String name;
    private Boolean isFile;
    protected FileSystemNode parent;
    public Map<String, FileSystemNode> children;

    public FileSystemNode(String s,boolean isFile) {
        this.name=s;
        this.isFile = isFile;
        children=new HashMap<>();
    }

    public Boolean isFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }
}
