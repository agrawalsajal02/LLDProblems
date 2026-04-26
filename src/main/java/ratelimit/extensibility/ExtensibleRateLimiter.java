package ratelimit.extensibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ratelimit.Limiter;
import ratelimit.LimiterConfig;
import ratelimit.LimiterFactory;
import ratelimit.RateLimitResult;

public final class ExtensibleRateLimiter {
    private final Map<String, List<ScopedBinding>> bindingsByEndpoint;

    public ExtensibleRateLimiter(List<EndpointScopedLimitConfig> endpointConfigs) {
        this.bindingsByEndpoint = new HashMap<>();
        LimiterFactory factory = new LimiterFactory();

        for (EndpointScopedLimitConfig endpointConfig : endpointConfigs) {
            List<ScopedBinding> bindings = new ArrayList<>();

            for (ScopedLimiterConfig scopedConfig : endpointConfig.getScopedLimiters()) {
                Limiter limiter = factory.create(
                    new LimiterConfig(
                        endpointConfig.getEndpoint(),
                        scopedConfig.getAlgorithmType(),
                        scopedConfig.getAlgoConfig()
                    )
                );
                bindings.add(new ScopedBinding(scopedConfig.getScope(), limiter));
            }

            bindingsByEndpoint.put(endpointConfig.getEndpoint(), bindings);
        }
    }

    public ScopedRateLimitResult allow(RateLimitRequest request) {
        List<ScopedBinding> bindings = bindingsByEndpoint.getOrDefault(request.getEndpoint(), new ArrayList<>());
        Map<LimitScope, RateLimitResult> resultsByScope = new LinkedHashMap<>();

        boolean allowed = true;
        int minRemaining = Integer.MAX_VALUE;
        long maxRetryAfterMs = 0L;

        for (ScopedBinding binding : bindings) {
            String key = extractKey(binding.scope, request);
            if (key == null || key.isEmpty()) {
                continue;
            }

            RateLimitResult result = binding.limiter.allow(key);
            resultsByScope.put(binding.scope, result);

            if (!result.isAllowed()) {
                allowed = false;
                if (result.getRetryAfterMs() != null) {
                    maxRetryAfterMs = Math.max(maxRetryAfterMs, result.getRetryAfterMs());
                }
            }
            minRemaining = Math.min(minRemaining, result.getRemaining());
        }

        if (minRemaining == Integer.MAX_VALUE) {
            minRemaining = 0;
        }

        return new ScopedRateLimitResult(
            allowed,
            minRemaining,
            allowed ? null : maxRetryAfterMs,
            resultsByScope
        );
    }

    private String extractKey(LimitScope scope, RateLimitRequest request) {
        switch (scope) {
            case USER_ID:
                return request.getUserId();
            case API_KEY:
                return request.getApiKey();
            case IP_ADDRESS:
                return request.getIpAddress();
            case GLOBAL_ENDPOINT:
                // Hinglish: global limiter ke liye sab requests same endpoint key share karenge.
                return "GLOBAL::" + request.getEndpoint();
            default:
                throw new IllegalArgumentException("Unsupported scope: " + scope);
        }
    }

    private static final class ScopedBinding {
        private final LimitScope scope;
        private final Limiter limiter;

        private ScopedBinding(LimitScope scope, Limiter limiter) {
            this.scope = scope;
            this.limiter = limiter;
        }
    }
}
