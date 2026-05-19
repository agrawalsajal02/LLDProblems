package loggingservice;

public interface Sink {
    void write(String formattedMessage) throws Exception;
}
