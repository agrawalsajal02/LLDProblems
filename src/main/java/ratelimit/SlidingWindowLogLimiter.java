package ratelimit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public final class SlidingWindowLogLimiter implements Limiter {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, RequestLog> logs;

    public SlidingWindowLogLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
        this.logs = new HashMap<>();
    }

    @Override
    public RateLimitResult allow(String key) {
        RequestLog log = getOrCreateLog(key);

        long now = System.currentTimeMillis();
        long cutoff = now - windowMs;

        while (!log.timestamps.isEmpty() && log.timestamps.peekFirst() < cutoff) {
            log.timestamps.pollFirst();
        }

        if (log.timestamps.size() < maxRequests) {
            log.timestamps.addLast(now);
            return new RateLimitResult(true, maxRequests - log.timestamps.size(), null);
        }

        long oldestTimestamp = log.timestamps.peekFirst();
        long retryAfterMs = (oldestTimestamp + windowMs) - now;
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private RequestLog getOrCreateLog(String key) {
        RequestLog log = logs.get(key);
        if (log == null) {
            log = new RequestLog();
            logs.put(key, log);
        }
        return log;
    }

    private static final class RequestLog {
        private final Deque<Long> timestamps = new ArrayDeque<>();
    }
}
