package ratelimit.dynamicconfig;

import ratelimit.AlgorithmType;
import ratelimit.LimiterConfig;

public final class UpdatableLimiterFactory {
    public UpdatableLimiter create(LimiterConfig config) {
        if (config.getAlgorithmType() == AlgorithmType.TOKEN_BUCKET) {
            return new UpdatableTokenBucketLimiter(
                getInt(config, "capacity"),
                getInt(config, "refillRatePerSecond")
            );
        }
        if (config.getAlgorithmType() == AlgorithmType.SLIDING_WINDOW_LOG) {
            return new UpdatableSlidingWindowLogLimiter(
                getInt(config, "maxRequests"),
                getLong(config, "windowMs")
            );
        }

        throw new IllegalArgumentException(
            "Updatable limiter not implemented for algorithm: " + config.getAlgorithmType()
        );
    }

    private int getInt(LimiterConfig config, String key) {
        Object value = config.getAlgoConfig().get(key);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Missing or invalid int config: " + key);
        }
        return ((Number) value).intValue();
    }

    private long getLong(LimiterConfig config, String key) {
        Object value = config.getAlgoConfig().get(key);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Missing or invalid long config: " + key);
        }
        return ((Number) value).longValue();
    }
}
