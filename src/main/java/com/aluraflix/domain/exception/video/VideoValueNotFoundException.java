package com.aluraflix.domain.exception.video;

import com.aluraflix.domain.exception.ValueNotFoundException;

public class VideoValueNotFoundException extends ValueNotFoundException {

    public VideoValueNotFoundException(String message) {
        super(message);
    }
}
