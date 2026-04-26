package ratelimit.dynamicconfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import ratelimit.AlgorithmType;
import ratelimit.LimiterConfig;

public final class DynamicConfigDemo {
    public static void main(String[] args) {
        Map<String, Object> initialBucketConfig = new HashMap<>();
        initialBucketConfig.put("capacity", 3);
        initialBucketConfig.put("refillRatePerSecond", 1);

        Map<String, Object> reloadedBucketConfig = new HashMap<>();
        reloadedBucketConfig.put("capacity", 10);
        reloadedBucketConfig.put("refillRatePerSecond", 5);

        LimiterConfig searchConfig = new LimiterConfig("/search", AlgorithmType.TOKEN_BUCKET, initialBucketConfig);
        LimiterConfig newSearchConfig = new LimiterConfig("/search", AlgorithmType.TOKEN_BUCKET, reloadedBucketConfig);
        LimiterConfig defaultConfig = new LimiterConfig("*", AlgorithmType.TOKEN_BUCKET, initialBucketConfig);

        ReloadableRateLimiter reloadable = new ReloadableRateLimiter(
            Arrays.asList(searchConfig),
            defaultConfig
        );
        System.out.println("Reloadable before swap: " + reloadable.allow("user-1", "/search"));
        System.out.println("Reloadable before swap: " + reloadable.allow("user-1", "/search"));
        reloadable.reloadConfig(Arrays.asList(newSearchConfig), defaultConfig);
        System.out.println("Reloadable after full swap (fresh state): " + reloadable.allow("user-1", "/search"));

        StatePreservingRateLimiter statePreserving = new StatePreservingRateLimiter(
            Arrays.asList(searchConfig),
            defaultConfig
        );
        System.out.println("State preserving before update: " + statePreserving.allow("user-1", "/search"));
        System.out.println("State preserving before update: " + statePreserving.allow("user-1", "/search"));
        statePreserving.updateEndpointConfig("/search", newSearchConfig);
        System.out.println("State preserving after config update: " + statePreserving.allow("user-1", "/search"));
    }
}
