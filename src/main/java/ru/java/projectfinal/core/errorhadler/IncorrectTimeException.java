package ru.java.projectfinal.core.errorhadler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectTimeException extends RuntimeException{
    public IncorrectTimeException(LocalDateTime time){
        super("Incorrect time specified, time can't be " + time);
    }
}
