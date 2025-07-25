package spring_blogs.spring_blogs.exception.exceptions;

public class UnauthorizedExceptionMessage extends RuntimeException {
    public UnauthorizedExceptionMessage(String message) {
        super(message);
    }
}
