package solidprinciples.dip.good;

public final class GoodDipDemo {
    public static void main(String[] args) {
        Windows98Machine machine = new Windows98Machine(new StandardKeyboard(), new Monitor());
        machine.boot();
    }
}
