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

        System.out.println("Current directory: " + fs.pwd());
        System.out.println("cd /home/user: " + fs.cd("/home/user"));
        System.out.println("Current directory: " + fs.pwd());
        System.out.println("mkdir downloads: " + fs.mkdir("downloads"));
        fs.rename("/home/user/downloads", "archive");
        System.out.println("Renamed /home/user/downloads to /home/user/archive");
        System.out.println("cd archive: " + fs.cd("archive"));
        System.out.println("Current directory: " + fs.pwd());
        System.out.println("cd ..: " + fs.cd(".."));
        System.out.println("Current directory: " + fs.pwd());
        fs.delete("/home/user/archive");
        System.out.println("Deleted /home/user/archive");
        System.out.println("cd *: " + fs.cd("*"));
        System.out.println("Current directory: " + fs.pwd());

        fs.get("/").display(0);
    }
}
