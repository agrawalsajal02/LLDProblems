package filesystem.practice;

public class Main {
    static void main() throws Exception {
        FileSystemManager fileSystemManager = new FileSystemManager();
        fileSystemManager.createFile("/home/etc/sample.txt");
    }
}
