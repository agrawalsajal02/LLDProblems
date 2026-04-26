package solidprinciples.dip.bad;

public class Windows98Machine {
    private final StandardKeyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine() {
        this.keyboard = new StandardKeyboard();
        this.monitor = new Monitor();
    }

    public void boot() {
        System.out.println("Machine booted with fixed dependencies: " + keyboard + " " + monitor);
    }
}
