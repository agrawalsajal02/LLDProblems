package ratelimit.dynamicconfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import ratelimit.LimiterConfig;
import ratelimit.RateLimitResult;

public final class UpdatableSlidingWindowLogLimiter implements UpdatableLimiter {
    private int maxRequests;
    private long windowMs;
    private final Map<String, RequestLog> logs;

    public UpdatableSlidingWindowLogLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
        this.logs = new HashMap<>();
    }

    @Override
    public synchronized RateLimitResult allow(String key) {
        RequestLog log = getOrCreateLog(key);

        long now = System.currentTimeMillis();
        cleanup(log, now);

        if (log.timestamps.size() < maxRequests) {
            log.timestamps.addLast(now);
            return new RateLimitResult(true, maxRequests - log.timestamps.size(), null);
        }

        long oldestTimestamp = log.timestamps.peekFirst();
        long retryAfterMs = (oldestTimestamp + windowMs) - now;
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    @Override
    public synchronized void updateConfig(LimiterConfig config) {
        this.maxRequests = getInt(config, "maxRequests");
        this.windowMs = getLong(config, "windowMs");

        long now = System.currentTimeMillis();
        for (RequestLog log : logs.values()) {
            cleanup(log, now);
        }
    }

    private void cleanup(RequestLog log, long now) {
        long cutoff = now - windowMs;
        while (!log.timestamps.isEmpty() && log.timestamps.peekFirst() < cutoff) {
            log.timestamps.pollFirst();
        }
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
