package NewsFeed.Exceptions;

public class NoUserFoundException extends Throwable {
    String message;

    public NoUserFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
