package com.aluraflix.domain.exception;

import lombok.Getter;

import java.util.List;

public class FieldNotAcceptableException extends RuntimeException {

    @Getter
    private List<String> errors;

    public FieldNotAcceptableException(String message) {
        super(message);
    }

    public FieldNotAcceptableException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
