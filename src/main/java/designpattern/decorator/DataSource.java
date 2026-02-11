package designpattern.decorator;

public interface DataSource {
    void writeData(String data);
    String readData();
}
