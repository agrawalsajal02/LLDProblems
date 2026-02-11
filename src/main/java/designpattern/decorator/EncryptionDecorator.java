package designpattern.decorator;

public class EncryptionDecorator implements DataSource {
    private final DataSource wrapped;

    public EncryptionDecorator(DataSource source) {
        this.wrapped = source;
    }

    @Override
    public void writeData(String data) {
        wrapped.writeData(encrypt(data));
    }

    @Override
    public String readData() {
        return decrypt(wrapped.readData());
    }

    private String encrypt(String data) {
        return "encrypted:" + data;
    }

    private String decrypt(String data) {
        return data.replace("encrypted:", "");
    }
}
