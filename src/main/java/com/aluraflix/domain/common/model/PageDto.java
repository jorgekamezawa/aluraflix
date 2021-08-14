package com.aluraflix.domain.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    @JsonProperty("lista")
    private List<T> listaDados;

    @JsonProperty("total_elementos")
    private long totalElementos;

    @JsonProperty("total_paginas")
    private long totalPaginas;
}
