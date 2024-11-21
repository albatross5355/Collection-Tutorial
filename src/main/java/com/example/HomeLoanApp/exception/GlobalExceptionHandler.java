package com.example.HomeLoanApp.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import javax.management.AttributeNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorHandlerFields> handleNullPointerException(
            NullPointerException exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.NULL_POINTER_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =        String path = request.getRequestURI();

        new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(AttributeNotFoundException.class)
    public ResponseEntity<ErrorHandlerFields> handleAttributeNotFoundException(
            AttributeNotFoundException exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorCode = Constants.ATTRIBUTE_NOT_FOUND_EXCEPTION;
        String message = exception.getMessage();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorHandlerFields> handleIllegalArgument(
            IllegalArgumentException exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.ILLEGAL_ARGUMENT_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandlerFields> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.METHOD_ARGUMENT_INVALID_EXCEPTION;
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorHandlerFields> handleJsonParseException(JsonParseException exception,
                                                                       HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.JSON_PARSE_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorHandlerFields> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorHandlerFields> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        String errorCode = Constants.CONSTRAINT_VIOLATION_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorHandlerFields> handleInvalidFormat(InvalidFormatException exception,
                                                                  HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorCode = Constants.INVALID_FORMAT_EXCEPTION;
        String message = exception.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorHandlerFields> handleResponseStatusException(
            ResponseStatusException ex, HttpServletRequest request) {
        HttpStatus status = ex.getStatus();
        String errorCode = Constants.RESPONSE_STATUS_EXCEPTION;
        String message = ex.getReason();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorHandlerFields> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String errorCode = Constants.HTTP_REQUEST_METHOD_NOT_SUPPORTED;
        String message = ex.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandlerFields> handleThrowable(Exception ex,
                                                              HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorCode = Constants.GENERAL_THROWABLE_EXCEPTION;
        String message = ex.getMessage();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String errorId = UUID.randomUUID().toString();
        ErrorHandlerFields error =
                new ErrorHandlerFields(errorId, errorCode, message, status.value(), status.name(), path,
                        method, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }
}