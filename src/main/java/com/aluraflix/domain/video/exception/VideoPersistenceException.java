package com.aluraflix.domain.video.exception;

import com.aluraflix.domain.exception.PersistenceException;

public class VideoPersistenceException extends PersistenceException {

    public VideoPersistenceException(String message) {
        super(message);
    }
}
