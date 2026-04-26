package ratelimit.dynamicconfig;

import java.util.HashMap;
import java.util.Map;
import ratelimit.LimiterConfig;
import ratelimit.RateLimitResult;

public final class UpdatableTokenBucketLimiter implements UpdatableLimiter {
    private int capacity;
    private int refillRatePerSecond;
    private final Map<String, TokenBucket> buckets;

    public UpdatableTokenBucketLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.buckets = new HashMap<>();
    }

    @Override
    public synchronized RateLimitResult allow(String key) {
        TokenBucket bucket = getOrCreateBucket(key);

        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;
        double tokensToAdd = (elapsed * refillRatePerSecond) / 1000.0;

        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTime = now;

        if (bucket.tokens >= 1.0) {
            bucket.tokens -= 1.0;
            return new RateLimitResult(true, (int) Math.floor(bucket.tokens), null);
        }

        double tokensNeeded = 1.0 - bucket.tokens;
        long retryAfterMs = (long) Math.ceil((tokensNeeded * 1000.0) / refillRatePerSecond);
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    @Override
    public synchronized void updateConfig(LimiterConfig config) {
        int newCapacity = getInt(config, "capacity");
        int newRefillRatePerSecond = getInt(config, "refillRatePerSecond");

        this.capacity = newCapacity;
        this.refillRatePerSecond = newRefillRatePerSecond;

        for (TokenBucket bucket : buckets.values()) {
            if (bucket.tokens > newCapacity) {
                bucket.tokens = newCapacity;
            }
        }
    }

    private int getInt(LimiterConfig config, String key) {
        Object value = config.getAlgoConfig().get(key);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Missing or invalid int config: " + key);
        }
        return ((Number) value).intValue();
    }

    private TokenBucket getOrCreateBucket(String key) {
        TokenBucket bucket = buckets.get(key);
        if (bucket == null) {
            bucket = new TokenBucket(capacity, System.currentTimeMillis());
            buckets.put(key, bucket);
        }
        return bucket;
    }

    private static final class TokenBucket {
        private double tokens;
        private long lastRefillTime;

        private TokenBucket(double tokens, long lastRefillTime) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }
}
