package ratelimit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class LimiterConfig {
    private final String endpoint;
    private final AlgorithmType algorithmType;
    private final Map<String, Object> algoConfig;

    public LimiterConfig(String endpoint, AlgorithmType algorithmType, Map<String, Object> algoConfig) {
        this.endpoint = endpoint;
        this.algorithmType = algorithmType;
        this.algoConfig = new HashMap<>(algoConfig);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public Map<String, Object> getAlgoConfig() {
        return Collections.unmodifiableMap(algoConfig);
    }
}
