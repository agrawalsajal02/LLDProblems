package solidprinciples.srp.bad;

public final class BadSrpDemo {
    public static void main(String[] args) {
        BadBook book = new BadBook("Clean Code", "Robert Martin", "Functions should do one thing.");
        book.printTextToConsole();
    }
}
