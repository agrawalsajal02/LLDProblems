package amazonlocker;

public final class LockerMain {
    public static void main(String[] args) {
        Compartment[] compartments = new Compartment[] {
            new Compartment("A1", Size.SMALL),
            new Compartment("B1", Size.MEDIUM),
            new Compartment("C1", Size.LARGE)
        };

        Locker locker = new Locker(compartments);
        String code = locker.depositPackage(Size.MEDIUM);
        System.out.println("Access code: " + code);

        locker.pickup(code);
    }
}
