package ratelimit;

import java.util.HashMap;
import java.util.Map;

public final class LeakyBucketLimiter implements Limiter {
    private final int capacity;
    private final int leakRatePerSecond;
    private final Map<String, BucketState> buckets;

    public LeakyBucketLimiter(int capacity, int leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.buckets = new HashMap<>();
    }

    @Override
    public RateLimitResult allow(String key) {
        BucketState state = getOrCreateState(key);

        long now = System.currentTimeMillis();
        long elapsed = now - state.lastLeakTime;
        double leaked = (elapsed * leakRatePerSecond) / 1000.0;

        state.waterLevel = Math.max(0.0, state.waterLevel - leaked);
        state.lastLeakTime = now;

        if (state.waterLevel + 1.0 <= capacity) {
            state.waterLevel += 1.0;
            int remaining = Math.max(0, (int) Math.floor(capacity - state.waterLevel));
            return new RateLimitResult(true, remaining, null);
        }

        double spaceNeeded = (state.waterLevel + 1.0) - capacity;
        long retryAfterMs = (long) Math.ceil((spaceNeeded * 1000.0) / leakRatePerSecond);
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private BucketState getOrCreateState(String key) {
        BucketState state = buckets.get(key);
        if (state == null) {
            state = new BucketState(0.0, System.currentTimeMillis());
            buckets.put(key, state);
        }
        return state;
    }

    private static final class BucketState {
        private double waterLevel;
        private long lastLeakTime;

        private BucketState(double waterLevel, long lastLeakTime) {
            this.waterLevel = waterLevel;
            this.lastLeakTime = lastLeakTime;
        }
    }
}
