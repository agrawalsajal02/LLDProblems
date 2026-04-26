package practiceSession1.DesignHitCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HitCounter {
    Integer lastNSeconds;
    Map<String,HitWindow>hitWindowMap;

    public HitCounter(Integer lastNSeconds) {
        this.lastNSeconds = lastNSeconds;
        this.hitWindowMap= new ConcurrentHashMap<String, HitWindow>() {
        };
    }

    public void hit(String url, int timestamp) {
        cleanWindow(url,timestamp);
        HitWindow hitWindow = hitWindowMap.computeIfAbsent(url,ignored->new HitWindow());
        hitWindow.recordHit(timestamp);
    }

    public int getHits(String url, int timestamp) {
        // clean the window
        cleanWindow(url, timestamp);
        HitWindow hitWindow = hitWindowMap.computeIfAbsent(url,ignored->new HitWindow());
        return hitWindow.getHits();
    }

    private void cleanWindow(String url, int currentTimestamp) {
        HitWindow hitWindow = hitWindowMap.get(url);
        if (hitWindow!=null){
            hitWindow.evictExpired(lastNSeconds,currentTimestamp);
        }
    }
}
