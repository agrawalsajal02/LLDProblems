package interviewpatterns.structural.decorator;

public final class DecoratorDemo {
    public static void main(String[] args) {
        DataSource source = new FileDataSource("demo.txt");
        source = new EncryptionDecorator(source);
        source = new CompressionDecorator(source);

        source.writeData("secret");
        System.out.println(source.readData());
    }
}
