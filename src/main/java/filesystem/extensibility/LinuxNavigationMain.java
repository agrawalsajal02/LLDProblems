package filesystem.extensibility;

public final class LinuxNavigationMain {
    public static void main(String[] args) {
        LinuxNavigationFileSystem fs = new LinuxNavigationFileSystem();

        fs.mkdir("home");
        fs.cd("/home");
        fs.mkdir("user");
        fs.cd("user");
        fs.mkdir("docs");

        System.out.println("PWD: " + fs.pwd());

        fs.cd("..");
        System.out.println("After cd .. : " + fs.pwd());

        fs.cd("*");
        System.out.println("After cd * : " + fs.pwd());
    }
}
