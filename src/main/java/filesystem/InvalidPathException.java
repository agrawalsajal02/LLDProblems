package filesystem;

public class InvalidPathException extends FileSystemException {
    public InvalidPathException(String message) {
        super(message);
    }
}
