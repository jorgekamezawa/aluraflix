package com.aluraflix.exception.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorModel {

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime localDateTime;
}
