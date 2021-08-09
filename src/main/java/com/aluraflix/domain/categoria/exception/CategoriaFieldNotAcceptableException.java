package com.aluraflix.domain.categoria.exception;

import com.aluraflix.domain.exception.FieldNotAcceptableException;

import java.util.List;

public class CategoriaFieldNotAcceptableException extends FieldNotAcceptableException {

    public CategoriaFieldNotAcceptableException(String message) {
        super(message);
    }

    public CategoriaFieldNotAcceptableException(String message, List<String> errors) {
        super(message, errors);
    }
}
