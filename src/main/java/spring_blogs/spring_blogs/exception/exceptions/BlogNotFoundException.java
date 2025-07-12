package spring_blogs.spring_blogs.exception.exceptions;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
