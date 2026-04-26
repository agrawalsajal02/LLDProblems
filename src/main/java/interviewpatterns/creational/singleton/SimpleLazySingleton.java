package interviewpatterns.creational.singleton;

public final class SimpleLazySingleton {
    private static SimpleLazySingleton instance;

    private SimpleLazySingleton() {
    }

    public static SimpleLazySingleton getInstance() {
        if (instance == null) {
            instance = new SimpleLazySingleton();
        }
        return instance;
    }

    public String name() {
        return "SimpleLazySingleton";
    }
}
