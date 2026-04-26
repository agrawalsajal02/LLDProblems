package interviewpatterns.creational.singleton;

public final class SingletonDemo {
    public static void main(String[] args) {
        System.out.println(SimpleLazySingleton.getInstance().name());
        System.out.println(EagerSingleton.getInstance().name());
        System.out.println(SynchronizedSingleton.getInstance().name());
        System.out.println(DoubleCheckedLockingSingleton.getInstance().name());
        System.out.println(BillPughSingleton.getInstance().name());
        System.out.println(EnumSingleton.INSTANCE.nameValue());
    }
}
