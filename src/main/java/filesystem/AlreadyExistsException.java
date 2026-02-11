package filesystem;

public class AlreadyExistsException extends FileSystemException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
