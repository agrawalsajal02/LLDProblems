package solidprinciples.dip.good;

public class Windows98Machine {
    private final Keyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine(Keyboard keyboard, Monitor monitor) {
        this.keyboard = keyboard;
        this.monitor = monitor;
    }

    public void boot() {
        System.out.println("Booting with " + keyboard.type() + " and " + monitor.model());
    }
}
