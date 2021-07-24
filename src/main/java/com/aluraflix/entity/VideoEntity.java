package com.aluraflix.entity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VideoEntity {

    private Long id;

    private String titulo;

    private String descricao;

    private String url;
}
