package interviewpatterns.creational.singleton;

public final class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public String name() {
        return "SynchronizedSingleton";
    }
}
