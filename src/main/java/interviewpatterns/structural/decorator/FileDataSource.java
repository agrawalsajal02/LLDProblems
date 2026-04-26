package interviewpatterns.structural.decorator;

public class FileDataSource implements DataSource {
    private final String fileName;
    private String data;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
        this.data = "";
    }

    @Override
    public void writeData(String data) {
        this.data = data;
        System.out.println("Writing to " + fileName + ": " + data);
    }

    @Override
    public String readData() {
        return data;
    }
}
