package com.aluraflix.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private Long id;

    private String titulo;

    private String descricao;

    private String url;
}
