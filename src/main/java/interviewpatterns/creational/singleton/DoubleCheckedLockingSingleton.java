package interviewpatterns.creational.singleton;

public final class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                // Hinglish: second check zaroori hai, warna do threads pehla null check pass karke duplicate object bana sakte hain.
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public String name() {
        return "DoubleCheckedLockingSingleton";
    }
}
