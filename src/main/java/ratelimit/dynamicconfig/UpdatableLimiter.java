package ratelimit.dynamicconfig;

import ratelimit.Limiter;
import ratelimit.LimiterConfig;

public interface UpdatableLimiter extends Limiter {
    void updateConfig(LimiterConfig config);
}
