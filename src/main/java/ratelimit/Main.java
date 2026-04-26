package ratelimit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Main {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> searchAlgo = new HashMap<>();
        searchAlgo.put("capacity", 3);
        searchAlgo.put("refillRatePerSecond", 1);

        Map<String, Object> uploadAlgo = new HashMap<>();
        uploadAlgo.put("maxRequests", 2);
        uploadAlgo.put("windowMs", 5000L);

        Map<String, Object> defaultAlgo = new HashMap<>();
        defaultAlgo.put("capacity", 2);
        defaultAlgo.put("refillRatePerSecond", 1);

        Map<String, Object> paymentAlgo = new HashMap<>();
        paymentAlgo.put("maxRequests", 2);
        paymentAlgo.put("windowMs", 4000L);

        Map<String, Object> analyticsAlgo = new HashMap<>();
        analyticsAlgo.put("maxRequests", 3);
        analyticsAlgo.put("windowMs", 4000L);

        Map<String, Object> exportAlgo = new HashMap<>();
        exportAlgo.put("capacity", 2);
        exportAlgo.put("leakRatePerSecond", 1);

        RateLimiter rateLimiter = new RateLimiter(
            Arrays.asList(
                new LimiterConfig("/search", AlgorithmType.TOKEN_BUCKET, searchAlgo),
                new LimiterConfig("/upload", AlgorithmType.SLIDING_WINDOW_LOG, uploadAlgo),
                new LimiterConfig("/payments", AlgorithmType.FIXED_WINDOW_COUNTER, paymentAlgo),
                new LimiterConfig("/analytics", AlgorithmType.SLIDING_WINDOW_COUNTER, analyticsAlgo),
                new LimiterConfig("/exports", AlgorithmType.LEAKY_BUCKET, exportAlgo)
            ),
            new LimiterConfig("*", AlgorithmType.TOKEN_BUCKET, defaultAlgo)
        );

        System.out.println("Token bucket on /search");
        System.out.println(rateLimiter.allow("user-1", "/search"));
        System.out.println(rateLimiter.allow("user-1", "/search"));
        System.out.println(rateLimiter.allow("user-1", "/search"));
        System.out.println(rateLimiter.allow("user-1", "/search"));

        Thread.sleep(1100);
        System.out.println(rateLimiter.allow("user-1", "/search"));

        System.out.println("\nSliding window on /upload");
        System.out.println(rateLimiter.allow("user-2", "/upload"));
        System.out.println(rateLimiter.allow("user-2", "/upload"));
        System.out.println(rateLimiter.allow("user-2", "/upload"));

        System.out.println("\nFixed window on /payments");
        System.out.println(rateLimiter.allow("user-4", "/payments"));
        System.out.println(rateLimiter.allow("user-4", "/payments"));
        System.out.println(rateLimiter.allow("user-4", "/payments"));

        System.out.println("\nSliding window counter on /analytics");
        System.out.println(rateLimiter.allow("user-5", "/analytics"));
        System.out.println(rateLimiter.allow("user-5", "/analytics"));
        System.out.println(rateLimiter.allow("user-5", "/analytics"));
        System.out.println(rateLimiter.allow("user-5", "/analytics"));

        System.out.println("\nLeaky bucket on /exports");
        System.out.println(rateLimiter.allow("user-6", "/exports"));
        System.out.println(rateLimiter.allow("user-6", "/exports"));
        System.out.println(rateLimiter.allow("user-6", "/exports"));

        System.out.println("\nDefault limiter on unknown endpoint");
        System.out.println(rateLimiter.allow("user-3", "/unknown"));
    }
}
