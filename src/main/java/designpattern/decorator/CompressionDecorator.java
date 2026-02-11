package designpattern.decorator;

public class CompressionDecorator implements DataSource {
    private final DataSource wrapped;

    public CompressionDecorator(DataSource source) {
        this.wrapped = source;
    }

    @Override
    public void writeData(String data) {
        wrapped.writeData(compress(data));
    }

    @Override
    public String readData() {
        return decompress(wrapped.readData());
    }

    private String compress(String data) {
        return "compressed:" + data;
    }

    private String decompress(String data) {
        return data.replace("compressed:", "");
    }
}
