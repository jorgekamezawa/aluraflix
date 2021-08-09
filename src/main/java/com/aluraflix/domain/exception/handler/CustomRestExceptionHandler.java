package com.aluraflix.domain.exception.handler;

import com.aluraflix.domain.common.util.DataUtil;
import com.aluraflix.domain.exception.FieldNotAcceptableException;
import com.aluraflix.domain.exception.PersistenceException;
import com.aluraflix.domain.exception.ValueNotFoundException;
import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.exception.CategoriaNoContentException;
import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.exception.model.ErrorModel;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.exception.VideoNoContentException;
import com.aluraflix.domain.video.exception.VideoPersistenceException;
import com.aluraflix.domain.video.exception.VideoValueNotFoundException;
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
                .localDateTime(DataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {VideoPersistenceException.class, CategoriaPersistenceException.class})
    public ResponseEntity<ErrorModel> handlerInternalServerErrorException(PersistenceException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(DataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {VideoFieldNotAcceptableException.class, CategoriaFieldNotAcceptableException.class})
    public ResponseEntity<ErrorModel> handlerFieldNotAcceptableException(FieldNotAcceptableException ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getErrors())
                .localDateTime(DataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }
}
