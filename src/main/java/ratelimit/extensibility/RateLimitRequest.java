package ratelimit.extensibility;

public final class RateLimitRequest {
    private final String endpoint;
    private final String userId;
    private final String apiKey;
    private final String ipAddress;

    public RateLimitRequest(String endpoint, String userId, String apiKey, String ipAddress) {
        this.endpoint = endpoint;
        this.userId = userId;
        this.apiKey = apiKey;
        this.ipAddress = ipAddress;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
