package com.aluraflix.domain.video.model;

import com.aluraflix.domain.categoria.model.Categoria;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Video {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("url")
    private String url;

    @JsonProperty("categoria")
    private Categoria categoria;
}
