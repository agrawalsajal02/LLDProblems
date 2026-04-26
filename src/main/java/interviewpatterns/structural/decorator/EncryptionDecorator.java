package interviewpatterns.structural.decorator;

public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource wrapped) {
        super(wrapped);
    }

    @Override
    public void writeData(String data) {
        wrapped.writeData("enc(" + data + ")");
    }

    @Override
    public String readData() {
        return wrapped.readData().replace("enc(", "").replace(")", "");
    }
}
