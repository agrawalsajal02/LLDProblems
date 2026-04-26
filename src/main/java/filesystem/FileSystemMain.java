package filesystem;

public final class FileSystemMain {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.createFolder("/home");
        fs.createFolder("/home/user");
        fs.createFile("/home/user/notes.txt", "hello");
        fs.createFolder("/home/user/docs");

        fs.move("/home/user/notes.txt", "/home/notes.txt");
        System.out.println(fs.get("/home/notes.txt").getPath());
        fs.get("/").display(0);
    }
}
