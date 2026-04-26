package ratelimit;

import java.util.HashMap;
import java.util.Map;

public final class FixedWindowCounterLimiter implements Limiter {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, WindowState> windows;

    public FixedWindowCounterLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
        this.windows = new HashMap<>();
    }

    @Override
    public RateLimitResult allow(String key) {
        long now = System.currentTimeMillis();
        long windowStart = now - (now % windowMs);

        WindowState state = windows.get(key);
        if (state == null || state.windowStart != windowStart) {
            state = new WindowState(windowStart, 0);
            windows.put(key, state);
        }

        if (state.count < maxRequests) {
            state.count++;
            return new RateLimitResult(true, maxRequests - state.count, null);
        }

        long retryAfterMs = (state.windowStart + windowMs) - now;
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private static final class WindowState {
        private final long windowStart;
        private int count;

        private WindowState(long windowStart, int count) {
            this.windowStart = windowStart;
            this.count = count;
        }
    }
}
