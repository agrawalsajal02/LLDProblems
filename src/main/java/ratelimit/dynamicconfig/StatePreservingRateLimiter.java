package ratelimit.dynamicconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ratelimit.LimiterConfig;
import ratelimit.RateLimitResult;

public final class StatePreservingRateLimiter {
    private final Map<String, UpdatableLimiter> limiters;
    private UpdatableLimiter defaultLimiter;
    private final UpdatableLimiterFactory factory;
    private LimiterConfig defaultConfig;

    public StatePreservingRateLimiter(List<LimiterConfig> configs, LimiterConfig defaultConfig) {
        this.limiters = new HashMap<>();
        this.factory = new UpdatableLimiterFactory();
        this.defaultConfig = defaultConfig;

        for (LimiterConfig config : configs) {
            if (config.getEndpoint() == null || config.getEndpoint().isEmpty()) {
                continue;
            }
            limiters.put(config.getEndpoint(), factory.create(config));
        }
        this.defaultLimiter = factory.create(defaultConfig);
    }

    public synchronized RateLimitResult allow(String clientId, String endpoint) {
        UpdatableLimiter limiter = limiters.getOrDefault(endpoint, defaultLimiter);
        return limiter.allow(clientId);
    }

    public synchronized void updateEndpointConfig(String endpoint, LimiterConfig newConfig) {
        UpdatableLimiter existingLimiter = limiters.get(endpoint);
        if (existingLimiter == null) {
            limiters.put(endpoint, factory.create(newConfig));
            return;
        }

        if (isSameAlgorithm(existingLimiter, newConfig)) {
            existingLimiter.updateConfig(newConfig);
            return;
        }

        // Hinglish: agar algorithm hi badal gaya to state compatible nahi hogi, isliye replace karna padega.
        limiters.put(endpoint, factory.create(newConfig));
    }

    public synchronized void updateDefaultConfig(LimiterConfig newDefaultConfig) {
        if (isSameAlgorithm(defaultLimiter, newDefaultConfig)) {
            defaultLimiter.updateConfig(newDefaultConfig);
        } else {
            defaultLimiter = factory.create(newDefaultConfig);
        }
        this.defaultConfig = newDefaultConfig;
    }

    private boolean isSameAlgorithm(UpdatableLimiter limiter, LimiterConfig config) {
        if (limiter instanceof UpdatableTokenBucketLimiter) {
            return config.getAlgorithmType() == ratelimit.AlgorithmType.TOKEN_BUCKET;
        }
        if (limiter instanceof UpdatableSlidingWindowLogLimiter) {
            return config.getAlgorithmType() == ratelimit.AlgorithmType.SLIDING_WINDOW_LOG;
        }
        return false;
    }

    public LimiterConfig getDefaultConfig() {
        return defaultConfig;
    }
}
