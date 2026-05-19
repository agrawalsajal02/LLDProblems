package loggingservice.extensibility;

import loggingservice.ConsoleSink;
import loggingservice.Destination;
import loggingservice.LogLevel;
import loggingservice.PlainTextFormatter;

import java.util.List;

public final class ExtensibilityMain {
    private ExtensibilityMain() {
    }

    public static void main(String[] args) throws Exception {
        AsyncDestination asyncConsole = new AsyncDestination(
                LogLevel.DEBUG,
                new PlainTextFormatter(),
                new ConsoleSink(),
                100,
                OverflowPolicy.DROP_NEW
        );

        LoggerFactory loggerFactory = new LoggerFactory(List.of(asyncConsole));
        NamedLogger paymentsLogger = loggerFactory.getLogger("com.app.payments");
        paymentsLogger.setConfiguredMinLevel(LogLevel.INFO);

        paymentsLogger.debug("filtered by named logger level");
        paymentsLogger.info("async named logger message");

        asyncConsole.close();
    }
}
