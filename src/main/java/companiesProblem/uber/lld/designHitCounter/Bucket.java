package companiesProblem.uber.lld.designHitCounter;

final class Bucket {
    private final int timestamp;
    private int count;

    Bucket(int timestamp, int count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    int getTimestamp() {
        return timestamp;
    }

    int getCount() {
        return count;
    }

    void increment() {
        count++;
    }
}
