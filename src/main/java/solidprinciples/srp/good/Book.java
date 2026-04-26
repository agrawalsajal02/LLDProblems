package solidprinciples.srp.good;

public class Book {
    private final String name;
    private final String author;
    private final String text;

    public Book(String name, String author, String text) {
        this.name = name;
        this.author = author;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String replaceWordInText(String word, String replacementWord) {
        return text.replace(word, replacementWord);
    }

    public boolean isWordInText(String word) {
        return text.contains(word);
    }
}
