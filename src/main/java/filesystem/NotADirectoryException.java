package filesystem;

public class NotADirectoryException extends FileSystemException {
    public NotADirectoryException(String message) {
        super(message);
    }
}
