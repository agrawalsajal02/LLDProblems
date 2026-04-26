package interviewpatterns.structural.decorator;

public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource wrapped) {
        super(wrapped);
    }

    @Override
    public void writeData(String data) {
        wrapped.writeData("zip(" + data + ")");
    }

    @Override
    public String readData() {
        return wrapped.readData().replace("zip(", "").replace(")", "");
    }
}
