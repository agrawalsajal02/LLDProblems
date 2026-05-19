package loggingservice.extensibility;

import loggingservice.Destination;
import loggingservice.LogFormatter;
import loggingservice.LogLevel;
import loggingservice.LogRecord;
import loggingservice.Sink;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AsyncDestination extends Destination implements AutoCloseable {
    private final LogLevel minLevel;
    private final LogFormatter formatter;
    private final Sink sink;
    private final BlockingQueue<LogRecord> queue;
    private final OverflowPolicy overflowPolicy;
    private final AtomicBoolean running;
    private final Thread worker;

    public AsyncDestination(
            LogLevel minLevel,
            LogFormatter formatter,
            Sink sink,
            int capacity,
            OverflowPolicy overflowPolicy
    ) {
        super(minLevel, formatter, sink);
        this.minLevel = minLevel;
        this.formatter = formatter;
        this.sink = sink;
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.overflowPolicy = overflowPolicy;
        this.running = new AtomicBoolean(true);
        this.worker = new Thread(this::drain, "async-log-destination");
        this.worker.start();
    }

    @Override
    public void write(LogRecord record) {
        if (!record.getLevel().isAtLeast(minLevel)) {
            return;
        }

        if (overflowPolicy == OverflowPolicy.BLOCK_PRODUCER) {
            enqueueBlocking(record);
            return;
        }

        boolean offered = queue.offer(record);
        if (!offered) {
            System.err.println("logger: async queue full, dropping log record");
        }
    }

    private void enqueueBlocking(LogRecord record) {
        try {
            queue.put(record);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.err.println("logger: interrupted while enqueueing log record");
        }
    }

    private void drain() {
        while (running.get() || !queue.isEmpty()) {
            try {
                LogRecord record = queue.poll(100, TimeUnit.MILLISECONDS);
                if (record == null) {
                    continue;
                }

                String formattedMessage = formatter.format(record);
                sink.write(formattedMessage);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                running.set(false);
            } catch (Exception exception) {
                System.err.println("logger: async sink write failed: " + exception.getMessage());
            }
        }
    }

    @Override
    public void close() throws InterruptedException {
        running.set(false);
        worker.join();
    }
}
