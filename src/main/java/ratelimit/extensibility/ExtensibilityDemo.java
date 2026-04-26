package ratelimit.extensibility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import ratelimit.AlgorithmType;

public final class ExtensibilityDemo {
    public static void main(String[] args) {
        Map<String, Object> userAlgo = new HashMap<>();
        userAlgo.put("maxRequests", 2);
        userAlgo.put("windowMs", 4000L);

        Map<String, Object> ipAlgo = new HashMap<>();
        ipAlgo.put("capacity", 3);
        ipAlgo.put("refillRatePerSecond", 1);

        Map<String, Object> globalAlgo = new HashMap<>();
        globalAlgo.put("capacity", 5);
        globalAlgo.put("refillRatePerSecond", 1);

        ExtensibleRateLimiter rateLimiter = new ExtensibleRateLimiter(
            Arrays.asList(
                new EndpointScopedLimitConfig(
                    "/search",
                    Arrays.asList(
                        new ScopedLimiterConfig(LimitScope.USER_ID, AlgorithmType.SLIDING_WINDOW_LOG, userAlgo),
                        new ScopedLimiterConfig(LimitScope.IP_ADDRESS, AlgorithmType.TOKEN_BUCKET, ipAlgo),
                        new ScopedLimiterConfig(LimitScope.GLOBAL_ENDPOINT, AlgorithmType.TOKEN_BUCKET, globalAlgo)
                    )
                )
            )
        );

        RateLimitRequest request = new RateLimitRequest("/search", "user-101", "api-abc", "10.0.0.1");

        System.out.println(rateLimiter.allow(request));
        System.out.println(rateLimiter.allow(request));
        System.out.println(rateLimiter.allow(request));
    }
}
