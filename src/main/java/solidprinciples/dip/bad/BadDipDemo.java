package solidprinciples.dip.bad;

public final class BadDipDemo {
    public static void main(String[] args) {
        Windows98Machine machine = new Windows98Machine();
        machine.boot();
    }
}
