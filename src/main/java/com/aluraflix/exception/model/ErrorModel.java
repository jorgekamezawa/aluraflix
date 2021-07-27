package com.aluraflix.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorModel {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;
    private final LocalDateTime localDateTime;
}
