package designpattern.decorator;

public final class DecoratorMain {
    public static void main(String[] args) {
        DataSource source = new FileDataSource("data.txt");
        source = new EncryptionDecorator(source);
        source = new CompressionDecorator(source);
        source.writeData("sensitive");
        System.out.println(source.readData());
    }
}
