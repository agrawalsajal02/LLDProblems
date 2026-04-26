package solidprinciples.srp.bad;

public class BadBook {
    private final String name;
    private final String author;
    private final String text;

    public BadBook(String name, String author, String text) {
        this.name = name;
        this.author = author;
        this.text = text;
    }

    public String replaceWordInText(String word, String replacementWord) {
        return text.replace(word, replacementWord);
    }

    public boolean isWordInText(String word) {
        return text.contains(word);
    }

    public void printTextToConsole() {
        System.out.println("Book: " + name + " by " + author);
        System.out.println(text);
    }
}
