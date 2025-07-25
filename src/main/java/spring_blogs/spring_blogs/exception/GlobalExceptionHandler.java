package spring_blogs.spring_blogs.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import spring_blogs.spring_blogs.exception.exceptions.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Catch 404 errors
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("statusCode", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", "The requested path " + ex.getRequestURL() + " was not found.");
        errorBody.put("timestamp", ZonedDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Map<String, String>> handleInternalServerException(InternalServerException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.put("message", exception.getMessage());
        response.put("error", "Internal Server Error");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBlogNotFound(BlogNotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.put("message", exception.getMessage());
        response.put("error", "Not Found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBlogNotFound(CommentNotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.put("message", exception.getMessage());
        response.put("error", "Not Found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> EmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        response.put("message", exception.getMessage());
        response.put("error", "Conflict");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedExceptionMessage.class)
    public ResponseEntity<Map<String, String>> UnauthorizedException(UnauthorizedExceptionMessage exception) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        response.put("message", exception.getMessage());
        response.put("error", "Unauthorized");

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


}
