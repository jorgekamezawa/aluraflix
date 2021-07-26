package com.aluraflix.exception.handler;

import com.aluraflix.exception.InternalServerErrorException;
import com.aluraflix.exception.NoContentException;
import com.aluraflix.exception.NotAcceptableException;
import com.aluraflix.exception.OkException;
import com.aluraflix.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<ErrorModel> handlerNoContentException(NoContentException ex){
        HttpStatus status = HttpStatus.NO_CONTENT;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {OkException.class})
    public ResponseEntity<ErrorModel> handlerOkException(OkException ex){
        HttpStatus status = HttpStatus.OK;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<ErrorModel> handlerInternalServerErrorException(InternalServerErrorException ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {NotAcceptableException.class})
    public ResponseEntity<ErrorModel> handlerNotAcceptableException(NotAcceptableException ex){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getErrors())
                .localDateTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorModel, status);
    }

}
