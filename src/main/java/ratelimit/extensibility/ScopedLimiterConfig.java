package ratelimit.extensibility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import ratelimit.AlgorithmType;

public final class ScopedLimiterConfig {
    private final LimitScope scope;
    private final AlgorithmType algorithmType;
    private final Map<String, Object> algoConfig;

    public ScopedLimiterConfig(LimitScope scope, AlgorithmType algorithmType, Map<String, Object> algoConfig) {
        this.scope = scope;
        this.algorithmType = algorithmType;
        this.algoConfig = new HashMap<>(algoConfig);
    }

    public LimitScope getScope() {
        return scope;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public Map<String, Object> getAlgoConfig() {
        return Collections.unmodifiableMap(algoConfig);
    }
}
