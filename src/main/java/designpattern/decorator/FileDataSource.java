package designpattern.decorator;

public class FileDataSource implements DataSource {
    private final String filename;
    private String data = "";

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) {
        this.data = data;
        System.out.println("Writing to " + filename + ": " + data);
    }

    @Override
    public String readData() {
        System.out.println("Reading from " + filename);
        return data;
    }
}
