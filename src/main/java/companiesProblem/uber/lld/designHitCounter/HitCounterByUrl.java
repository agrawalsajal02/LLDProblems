package companiesProblem.uber.lld.designHitCounter;

import java.util.HashMap;
import java.util.Map;

public final class HitCounterByUrl implements HitCounter {
    private static final int DEFAULT_WINDOW_SIZE_IN_SECONDS = 300;

    private final int windowSizeInSeconds;
    private final Map<String, UrlHitWindow> keyToWindow;

    public HitCounterByUrl() {
        this(DEFAULT_WINDOW_SIZE_IN_SECONDS);
    }

    public HitCounterByUrl(int windowSizeInSeconds) {
        if (windowSizeInSeconds <= 0) {
            throw new IllegalArgumentException("Window size must be positive.");
        }
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.keyToWindow = new HashMap<>();
    }

    @Override
    public void hit(String key, int timestamp) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key is required.");
        }
        UrlHitWindow hitWindow = keyToWindow.computeIfAbsent(key, ignored -> new UrlHitWindow());
        hitWindow.recordHit(timestamp, windowSizeInSeconds);
    }

    @Override
    public int getHits(String key, int timestamp) {
        UrlHitWindow hitWindow = keyToWindow.get(key);
        if (hitWindow == null) {
            return 0;
        }
        return hitWindow.getHits(timestamp, windowSizeInSeconds);
    }
}
