package ratelimit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RateLimiter {
    private final Map<String, Limiter> limiters;
    private final Limiter defaultLimiter;

    public RateLimiter(List<LimiterConfig> configs, LimiterConfig defaultConfig) {
        this.limiters = new HashMap<>();
        LimiterFactory factory = new LimiterFactory();

        for (LimiterConfig config : configs) {
            if (config.getEndpoint() == null || config.getEndpoint().isEmpty()) {
                continue;
            }
            limiters.put(config.getEndpoint(), factory.create(config));
        }

        this.defaultLimiter = factory.create(defaultConfig);
    }

    public RateLimitResult allow(String clientId, String endpoint) {
        Limiter limiter = limiters.getOrDefault(endpoint, defaultLimiter);
        return limiter.allow(clientId);
    }
}
