package interviewpatterns.creational.singleton;

public final class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public String name() {
        return "BillPughSingleton";
    }
}
