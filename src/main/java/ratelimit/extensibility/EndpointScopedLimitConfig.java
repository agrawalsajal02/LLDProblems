package ratelimit.extensibility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class EndpointScopedLimitConfig {
    private final String endpoint;
    private final List<ScopedLimiterConfig> scopedLimiters;

    public EndpointScopedLimitConfig(String endpoint, List<ScopedLimiterConfig> scopedLimiters) {
        this.endpoint = endpoint;
        this.scopedLimiters = new ArrayList<>(scopedLimiters);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public List<ScopedLimiterConfig> getScopedLimiters() {
        return Collections.unmodifiableList(scopedLimiters);
    }
}
