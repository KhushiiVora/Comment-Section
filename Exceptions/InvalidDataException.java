package NewsFeed.Exceptions;

public class InvalidDataException extends Throwable {
    String message;

    public InvalidDataException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
