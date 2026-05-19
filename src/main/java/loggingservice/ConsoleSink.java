package loggingservice;

import java.io.PrintStream;

public final class ConsoleSink implements Sink {
    private final PrintStream out;

    public ConsoleSink() {
        this(System.out);
    }

    public ConsoleSink(PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(String formattedMessage) {
        out.println(formattedMessage);
    }
}
