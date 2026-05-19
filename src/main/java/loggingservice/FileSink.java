package loggingservice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FileSink implements Sink, AutoCloseable {
    private final BufferedWriter writer;

    public FileSink(String filePath) throws IOException {
        this(Path.of(filePath));
    }

    public FileSink(Path filePath) throws IOException {
        this.writer = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    @Override
    public void write(String formattedMessage) throws IOException {
        writer.write(formattedMessage);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
