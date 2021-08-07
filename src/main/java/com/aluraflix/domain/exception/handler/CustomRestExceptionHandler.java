package com.aluraflix.domain.exception.handler;

import com.aluraflix.domain.exception.FieldNotAcceptableException;
import com.aluraflix.domain.exception.PersistenceException;
import com.aluraflix.domain.exception.ValueNotFoundException;
import com.aluraflix.domain.exception.categoria.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.exception.categoria.CategoriaNoContentException;
import com.aluraflix.domain.exception.categoria.CategoriaPersistenceException;
import com.aluraflix.domain.exception.categoria.CategoriaValueNotFoundException;
import com.aluraflix.domain.exception.model.ErrorModel;
import com.aluraflix.domain.exception.video.VideoFieldNotAcceptableException;
import com.aluraflix.domain.exception.video.VideoNoContentException;
import com.aluraflix.domain.exception.video.VideoPersistenceException;
import com.aluraflix.domain.exception.video.VideoValueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(value = {VideoNoContentException.class, CategoriaNoContentException.class})
    public ResponseEntity<ErrorModel> handlerNoContentException() {
        HttpStatus status = HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(status);
    }

    @ExceptionHandler(value = {VideoValueNotFoundException.class, CategoriaValueNotFoundException.class})
    public ResponseEntity<ErrorModel> handlerValueNotFoundException(ValueNotFoundException ex) {
        HttpStatus status = HttpStatus.OK;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {VideoPersistenceException.class, CategoriaPersistenceException.class})
    public ResponseEntity<ErrorModel> handlerInternalServerErrorException(PersistenceException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {VideoFieldNotAcceptableException.class, CategoriaFieldNotAcceptableException.class})
    public ResponseEntity<ErrorModel> handlerFieldNotAcceptableException(FieldNotAcceptableException ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getErrors())
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }
}
