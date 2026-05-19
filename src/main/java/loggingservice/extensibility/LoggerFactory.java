package loggingservice.extensibility;

import loggingservice.Destination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoggerFactory {
    private final Map<String, NamedLogger> registry;
    private final List<Destination> defaultDestinations;

    public LoggerFactory(List<Destination> defaultDestinations) {
        this.registry = new HashMap<>();
        this.defaultDestinations = defaultDestinations;
        registry.put("", new NamedLogger("", null, defaultDestinations));
    }

    public synchronized NamedLogger getLogger(String name) {
        if (name == null || name.isEmpty()) {
            return registry.get("");
        }

        NamedLogger existing = registry.get(name);
        if (existing != null) {
            return existing;
        }

        NamedLogger parent = getLogger(parentName(name));
        NamedLogger logger = new NamedLogger(name, parent, defaultDestinations);
        registry.put(name, logger);
        return logger;
    }

    private String parentName(String name) {
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1) {
            return "";
        }
        return name.substring(0, lastDot);
    }
}
