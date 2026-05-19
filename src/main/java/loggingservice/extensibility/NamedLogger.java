package loggingservice.extensibility;

import loggingservice.Destination;
import loggingservice.LogLevel;
import loggingservice.Logger;

import java.util.List;

public final class NamedLogger extends Logger {
    private final String name;
    private final NamedLogger parent;
    private LogLevel configuredMinLevel;

    public NamedLogger(String name, NamedLogger parent, List<Destination> destinations) {
        super(destinations);
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public NamedLogger getParent() {
        return parent;
    }

    public void setConfiguredMinLevel(LogLevel configuredMinLevel) {
        this.configuredMinLevel = configuredMinLevel;
    }

    public LogLevel getEffectiveMinLevel() {
        if (configuredMinLevel != null) {
            return configuredMinLevel;
        }
        if (parent != null) {
            return parent.getEffectiveMinLevel();
        }
        return LogLevel.DEBUG;
    }

    @Override
    public void log(LogLevel level, String message) {
        if (!level.isAtLeast(getEffectiveMinLevel())) {
            return;
        }
        super.log(level, "[" + name + "] " + message);
    }
}
