package filesystem.practice;

import java.util.HashMap;
import java.util.Map;

public class FileSystemManager {
    FileSystemNode root;

    public FileSystemManager() {
        this.root = new Folder("/");
    }

    public void createFile(String path) throws Exception {
        // it should contain file name in the end and path should be valid

//        if (!isValidPath(path)) {
//            throw new Exception("not a valid path");
//        }

        FileSystemNode parent = extractParent(path);
        String fileName = extractName(path);

        // validate if that file already exits or not on that file


        parent.children.put(fileName, new File(fileName));
    }

    private String extractName(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];

        //int lastSlash = path.lastIndexOf('/');
        //return path.substring(lastSlash + 1);
    }

    private FileSystemNode extractParent(String path) {
        int index = path.lastIndexOf('/');
        String parent = path.substring(0, index);

        FileSystemNode fileSystemNode = resolveParent(parent);
        return fileSystemNode;
    }

    private FileSystemNode resolveParent(String path) {
        FileSystemNode temp = root;
        String[] split = path.substring(1).split("/");
        for (int i = 0; i < split.length; i++) {
            String name = split[i];

            if (temp.isFile()) {
                throw new RuntimeException("Not found");
            }

            FileSystemNode fileSystemNode = temp.children.get(name);
            if (fileSystemNode == null) {
                throw new RuntimeException("Not found");
            }
            temp = fileSystemNode;
        }
        return temp;
    }


    private boolean isValidPath(String path) {
        return true;
    }


    public void createFolder(String s) {
    }
}
