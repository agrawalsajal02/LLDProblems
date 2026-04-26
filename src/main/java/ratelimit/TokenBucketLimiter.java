package ratelimit;

import java.util.HashMap;
import java.util.Map;

public final class TokenBucketLimiter implements Limiter {
    private final int capacity;
    private final int refillRatePerSecond;
    private final Map<String, TokenBucket> buckets;

    public TokenBucketLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.buckets = new HashMap<>();
    }

    @Override
    public RateLimitResult allow(String key) {
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
