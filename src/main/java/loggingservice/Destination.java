package loggingservice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Destination {
    private final LogLevel minLevel;
    private final LogFormatter formatter;
    private final Sink sink;
    private final Lock lock;

    public Destination(LogLevel minLevel, LogFormatter formatter, Sink sink) {
        this.minLevel = minLevel;
        this.formatter = formatter;
        this.sink = sink;
        this.lock = new ReentrantLock();
    }

    public void write(LogRecord record) {
        if (!record.getLevel().isAtLeast(minLevel)) {
            return;
        }

        String formattedMessage = formatter.format(record);
        lock.lock();
        try {
            sink.write(formattedMessage);
        } catch (Exception exception) {
            System.err.println("logger: sink write failed: " + exception.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
