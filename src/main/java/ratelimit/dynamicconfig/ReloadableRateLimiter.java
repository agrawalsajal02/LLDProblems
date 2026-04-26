package ratelimit.dynamicconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ratelimit.Limiter;
import ratelimit.LimiterConfig;
import ratelimit.LimiterFactory;
import ratelimit.RateLimitResult;

public final class ReloadableRateLimiter {
    private volatile Map<String, Limiter> limiters;
    private volatile Limiter defaultLimiter;

    public ReloadableRateLimiter(List<LimiterConfig> configs, LimiterConfig defaultConfig) {
        LimiterFactory factory = new LimiterFactory();
        this.limiters = buildLimiters(configs, factory);
        this.defaultLimiter = factory.create(defaultConfig);
    }

    public RateLimitResult allow(String clientId, String endpoint) {
        Limiter limiter = limiters.getOrDefault(endpoint, defaultLimiter);
        return limiter.allow(clientId);
    }

    public synchronized void reloadConfig(List<LimiterConfig> configs, LimiterConfig defaultConfig) {
        LimiterFactory factory = new LimiterFactory();
        Map<String, Limiter> newLimiters = buildLimiters(configs, factory);
        Limiter newDefaultLimiter = factory.create(defaultConfig);

        // Hinglish: poora naya map pehle banao, phir ek shot me reference swap karo.
        this.limiters = newLimiters;
        this.defaultLimiter = newDefaultLimiter;
    }

    private Map<String, Limiter> buildLimiters(List<LimiterConfig> configs, LimiterFactory factory) {
        Map<String, Limiter> built = new HashMap<>();
        for (LimiterConfig config : configs) {
            if (config.getEndpoint() == null || config.getEndpoint().isEmpty()) {
                continue;
            }
            built.put(config.getEndpoint(), factory.create(config));
        }
        return built;
    }
}
