package com.aluraflix.domain.exception;

import lombok.Getter;

import java.util.List;

public class NotAcceptableException extends RuntimeException {

    @Getter
    private List<String> errors;

    public NotAcceptableException(String message) {
        super(message);
    }

    public NotAcceptableException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
