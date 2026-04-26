package ratelimit;

import java.util.Map;

public final class LimiterFactory {
    public Limiter create(LimiterConfig config) {
        Map<String, Object> algoConfig = config.getAlgoConfig();

        switch (config.getAlgorithmType()) {
            case TOKEN_BUCKET:
                return new TokenBucketLimiter(
                    getInt(algoConfig, "capacity"),
                    getInt(algoConfig, "refillRatePerSecond")
                );
            case SLIDING_WINDOW_LOG:
                return new SlidingWindowLogLimiter(
                    getInt(algoConfig, "maxRequests"),
                    getLong(algoConfig, "windowMs")
                );
            case FIXED_WINDOW_COUNTER:
                return new FixedWindowCounterLimiter(
                    getInt(algoConfig, "maxRequests"),
                    getLong(algoConfig, "windowMs")
                );
            case SLIDING_WINDOW_COUNTER:
                return new SlidingWindowCounterLimiter(
                    getInt(algoConfig, "maxRequests"),
                    getLong(algoConfig, "windowMs")
                );
            case LEAKY_BUCKET:
                return new LeakyBucketLimiter(
                    getInt(algoConfig, "capacity"),
                    getInt(algoConfig, "leakRatePerSecond")
                );
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + config.getAlgorithmType());
        }
    }

    private int getInt(Map<String, Object> config, String key) {
        Object value = config.get(key);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Missing or invalid int config: " + key);
        }
        return ((Number) value).intValue();
    }

    private long getLong(Map<String, Object> config, String key) {
        Object value = config.get(key);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Missing or invalid long config: " + key);
        }
        return ((Number) value).longValue();
    }
}
