package ru.java.projectfinal.core.errorhadler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoinNotFoundException extends NullPointerException{

    public CoinNotFoundException(String s) {
        super(String.format("Coin with this name = %s was not found", s));
    }
}