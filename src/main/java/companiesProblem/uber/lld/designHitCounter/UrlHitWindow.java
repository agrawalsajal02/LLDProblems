package companiesProblem.uber.lld.designHitCounter;

import java.util.ArrayDeque;
import java.util.Deque;

final class UrlHitWindow {
    private final Deque<Bucket> buckets;
    private int totalCount;

    UrlHitWindow() {
        this.buckets = new ArrayDeque<>();
        this.totalCount = 0;
    }

    void recordHit(int timestamp, int windowSizeInSeconds) {
        evictExpired(timestamp, windowSizeInSeconds);

        if (!buckets.isEmpty() && buckets.peekLast().getTimestamp() == timestamp) {
            buckets.peekLast().increment();
        } else {
            buckets.offerLast(new Bucket(timestamp, 1));
        }
        totalCount++;
    }

    int getHits(int timestamp, int windowSizeInSeconds) {
        evictExpired(timestamp, windowSizeInSeconds);
        return totalCount;
    }

    private void evictExpired(int currentTimestamp, int windowSizeInSeconds) {
        while (!buckets.isEmpty()
            && buckets.peekFirst().getTimestamp() <= currentTimestamp - windowSizeInSeconds) {
            Bucket expiredBucket = buckets.pollFirst();
            totalCount -= expiredBucket.getCount();
        }
    }
}
