package vn.gqhao.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //Exception: MethodArgumentNotValidException - Http Status: 400 BAD REQUEST - Message: [Email invalid format !]
    //Exception: ConstraintViolationException - Http Status: 500 Internal Server Error - Message: must be ...
    //Exception: HandlerMethodValidationException - Http Status: 400 BAD REQUEST - Message: Validation failure
    //Exception: HttpMessageNotReadableException - Http Status: 400 BAD REQUEST - Message: Validation failure
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class,
            HandlerMethodValidationException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDateTime(LocalDate.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        // request.getDescription(false).replace("uri=", "") : get path request
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        // get message error by substring []
        String errorMessage = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {
            log.error("log: MethodArgumentNotValidException");
            errorResponse.setError("Payload Invalid !");
            int start = errorMessage.lastIndexOf("[");
            int end = errorMessage.lastIndexOf("]");
            errorMessage = errorMessage.substring(start + 1, end - 1);
        } else if (e instanceof ConstraintViolationException) {
            log.error("log: ConstraintViolationException");
            errorResponse.setError("Path Variable Invalid !");
            errorMessage = errorMessage.substring(errorMessage.indexOf(" ") + 1);
        } else if (e instanceof HandlerMethodValidationException) {
            log.error("log: HandlerMethodValidationException");
            errorResponse.setError("Path variable invalid !");
            errorMessage = errorMessage.substring(errorMessage.indexOf("V"));
        } else if (e instanceof HttpMessageNotReadableException) {
            errorResponse.setError("Payload invalid !");
            errorMessage = errorMessage.substring(errorMessage.indexOf(")") + 2);
        }
        errorResponse.setMessage(errorMessage);
        return errorResponse;
    }

    //Exception: MethodArgumentTypeMismatchException - Http Status: 500
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMethodArgumentTypeMismatchException(Exception e, WebRequest request) {
        log.error("log: MethodArgumentTypeMismatchException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDateTime(LocalDate.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        if (e instanceof MethodArgumentTypeMismatchException) {
            errorResponse.setMessage("Failed to convert value of type !");
        }
        return errorResponse;
    }

    //Exception: HttpRequestMethodNotSupportedException - Http Status: 405 Method not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(Exception e, WebRequest request) {
        log.error("log: HttpRequestMethodNotSupportedException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDateTime(LocalDate.now());
        errorResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError("Path variable invalid !");
        return errorResponse;
    }

    //Custom Exception: ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(Exception e, WebRequest request) {
        log.error("log: ResourceNotFoundException");
        return new ErrorResponse(LocalDate.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage());
    }
}
