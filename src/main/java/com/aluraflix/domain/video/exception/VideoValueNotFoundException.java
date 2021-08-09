package com.aluraflix.domain.video.exception;

import com.aluraflix.domain.exception.ValueNotFoundException;

public class VideoValueNotFoundException extends ValueNotFoundException {

    public VideoValueNotFoundException(String message) {
        super(message);
    }
}
