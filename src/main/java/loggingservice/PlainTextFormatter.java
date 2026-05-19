package loggingservice;

public final class PlainTextFormatter implements LogFormatter {
    @Override
    public String format(LogRecord record) {
        return record.getTimestamp()
                + " [" + record.getLevel() + "]"
                + " [" + record.getThreadName() + "] "
                + record.getMessage();
    }
}
