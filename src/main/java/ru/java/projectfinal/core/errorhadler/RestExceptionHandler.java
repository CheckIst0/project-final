package ru.java.projectfinal.core.errorhadler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<Object> handleIdNotFoundEx(IdNotFoundException ex, WebRequest request){
        ApiError apiError = new ApiError("Not Found", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CoinNotFoundException.class)
    protected ResponseEntity<Object> handleCoinNotFoundException(CoinNotFoundException ex, WebRequest request){
        ApiError apiError = new ApiError("Not Found", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OverLimitException.class)
    protected ResponseEntity<Object> handleOverLimitException(OverLimitException ex, WebRequest request){
        ApiError apiError = new ApiError("Limit is incorrect", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectTimeException.class)
    protected ResponseEntity<Object> handleIncorrectTimeException(IncorrectTimeException ex, WebRequest request){
        ApiError apiError = new ApiError("Time is incorrect", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}