package com.aluraflix.domain.exception.video;

import com.aluraflix.domain.exception.FieldNotAcceptableException;

import java.util.List;

public class VideoFieldNotAcceptableException extends FieldNotAcceptableException {

    public VideoFieldNotAcceptableException(String message) {
        super(message);
    }

    public VideoFieldNotAcceptableException(String message, List<String> errors) {
        super(message, errors);
    }
}
