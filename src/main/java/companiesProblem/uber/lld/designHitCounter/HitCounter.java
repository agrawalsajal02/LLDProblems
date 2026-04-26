package companiesProblem.uber.lld.designHitCounter;

public interface HitCounter {
    void hit(String key, int timestamp);

    int getHits(String key, int timestamp);
}
