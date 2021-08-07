package com.aluraflix.domain.exception.categoria;

import com.aluraflix.domain.exception.ValueNotFoundException;

public class CategoriaValueNotFoundException extends ValueNotFoundException {

    public CategoriaValueNotFoundException(String message) {
        super(message);
    }
}
