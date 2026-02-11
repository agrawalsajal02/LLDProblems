package filesystem;

public class NotFoundException extends FileSystemException {
    public NotFoundException(String message) {
        super(message);
    }
}
