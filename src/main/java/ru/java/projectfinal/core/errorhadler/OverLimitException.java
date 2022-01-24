package ru.java.projectfinal.core.errorhadler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OverLimitException extends RuntimeException{

    public OverLimitException() {
        super("The limit parameter cannot be less than 1 and more than 2000");
    }
}
