package ratelimit.extensibility;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import ratelimit.RateLimitResult;

public final class ScopedRateLimitResult {
    private final boolean allowed;
    private final int remaining;
    private final Long retryAfterMs;
    private final Map<LimitScope, RateLimitResult> resultsByScope;

    public ScopedRateLimitResult(
        boolean allowed,
        int remaining,
        Long retryAfterMs,
        Map<LimitScope, RateLimitResult> resultsByScope
    ) {
        this.allowed = allowed;
        this.remaining = remaining;
        this.retryAfterMs = retryAfterMs;
        this.resultsByScope = new LinkedHashMap<>(resultsByScope);
    }

    public boolean isAllowed() {
        return allowed;
    }

    public int getRemaining() {
        return remaining;
    }

    public Long getRetryAfterMs() {
        return retryAfterMs;
    }

    public Map<LimitScope, RateLimitResult> getResultsByScope() {
        return Collections.unmodifiableMap(resultsByScope);
    }

    @Override
    public String toString() {
        return "ScopedRateLimitResult{"
            + "allowed=" + allowed
            + ", remaining=" + remaining
            + ", retryAfterMs=" + retryAfterMs
            + ", resultsByScope=" + resultsByScope
            + '}';
    }
}
