package practiceSession1.DesignHitCounter;

import java.util.ArrayDeque;
import java.util.Deque;

public class HitWindow {
    Deque<Bucket> window;
     int totalCount;

    public HitWindow() {
        this.window = new ArrayDeque<>();
    }

    public synchronized void evictExpired(Integer lastNMinutes, int currentTimestamp) {
        while (window.size() > 0 && currentTimestamp - window.getFirst().timestamp > lastNMinutes) {
            Bucket bucket = window.pollFirst();
            totalCount= totalCount - bucket.count;

        }
    }

    public synchronized void recordHit(int timestamp) {
        if (window.size() > 0 && window.peekLast().timestamp == timestamp) {
            window.peekLast().count++;
            totalCount++;
            return;
        }
        totalCount++;
        window.add(new Bucket(timestamp, 1));
    }

    public synchronized int getHits() {
        return totalCount;
    }
}
