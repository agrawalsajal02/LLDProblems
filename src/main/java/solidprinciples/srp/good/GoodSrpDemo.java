package solidprinciples.srp.good;

public final class GoodSrpDemo {
    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert Martin", "Functions should do one thing.");
        BookPrinter printer = new BookPrinter();
        printer.printTextToConsole(book.getText());
    }
}
