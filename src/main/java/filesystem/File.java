package filesystem;

public class File extends FileSystemNode {
    private String content;

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        System.out.println("  ".repeat(Math.max(0, depth)) + "- " + getName());
    }
}
