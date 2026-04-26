package ratelimit;

public interface Limiter {
    RateLimitResult allow(String key);
}
