package loggingservice;

import java.nio.file.Path;
import java.util.List;

public final class LoggingServiceMain {
    private LoggingServiceMain() {
    }

    public static void main(String[] args) throws Exception {
        Path logFile = Path.of("/tmp/lld-logging-service.log");
        FileSink fileSink = new FileSink(logFile);

        Destination consoleDestination = new Destination(
                LogLevel.DEBUG,
                new PlainTextFormatter(),
                new ConsoleSink()
        );

        Destination fileDestination = new Destination(
                LogLevel.WARN,
                new JsonFormatter(),
                fileSink
        );

        Logger logger = new Logger(List.of(consoleDestination, fileDestination));

        try {
            logger.debug("debug appears only on console");
            logger.info("user signed in");
            logger.warn("disk space is low");
            logger.error("payment failed");
        } finally {
            fileSink.close();
        }

        System.out.println("File logs written to: " + logFile);
    }
}
