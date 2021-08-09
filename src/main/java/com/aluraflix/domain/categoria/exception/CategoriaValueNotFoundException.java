package com.aluraflix.domain.categoria.exception;

import com.aluraflix.domain.exception.ValueNotFoundException;

public class CategoriaValueNotFoundException extends ValueNotFoundException {

    public CategoriaValueNotFoundException(String message) {
        super(message);
    }
}
