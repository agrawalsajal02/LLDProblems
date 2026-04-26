package ratelimit;

import java.util.HashMap;
import java.util.Map;

public final class SlidingWindowCounterLimiter implements Limiter {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, CounterState> states;

    public SlidingWindowCounterLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
        this.states = new HashMap<>();
    }

    @Override
    public RateLimitResult allow(String key) {
        long now = System.currentTimeMillis();
        long currentWindowStart = now - (now % windowMs);

        CounterState state = states.get(key);
        if (state == null) {
            state = new CounterState(currentWindowStart);
            states.put(key, state);
        }

        rotateIfNeeded(state, currentWindowStart);

        double elapsedInWindow = now - state.currentWindowStart;
        double weight = (windowMs - elapsedInWindow) / windowMs;
        double effectiveCount = (state.previousCount * weight) + state.currentCount;

        if (effectiveCount < maxRequests) {
            state.currentCount++;
            int remaining = Math.max(0, (int) Math.floor(maxRequests - (effectiveCount + 1)));
            return new RateLimitResult(true, remaining, null);
        }

        long retryAfterMs = (state.currentWindowStart + windowMs) - now;
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private void rotateIfNeeded(CounterState state, long currentWindowStart) {
        if (state.currentWindowStart == currentWindowStart) {
            return;
        }

        long diff = currentWindowStart - state.currentWindowStart;
        if (diff >= 2 * windowMs) {
            state.previousCount = 0;
            state.currentCount = 0;
            state.currentWindowStart = currentWindowStart;
            return;
        }

        state.previousCount = state.currentCount;
        state.currentCount = 0;
        state.currentWindowStart = currentWindowStart;
    }

    private static final class CounterState {
        private long currentWindowStart;
        private int currentCount;
        private int previousCount;

        private CounterState(long currentWindowStart) {
            this.currentWindowStart = currentWindowStart;
            this.currentCount = 0;
            this.previousCount = 0;
        }
    }
}
