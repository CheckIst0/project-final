package ru.java.projectfinal.core.errorhadler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends EntityNotFoundException {

    public IdNotFoundException(Long id){
        super("Entity is not found, id = "+id);
    }
}