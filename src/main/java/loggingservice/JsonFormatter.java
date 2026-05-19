package loggingservice;

public final class JsonFormatter implements LogFormatter {
    @Override
    public String format(LogRecord record) {
        return "{"
                + "\"timestamp\":\"" + escape(record.getTimestamp().toString()) + "\","
                + "\"level\":\"" + record.getLevel() + "\","
                + "\"thread\":\"" + escape(record.getThreadName()) + "\","
                + "\"message\":\"" + escape(record.getMessage()) + "\""
                + "}";
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
