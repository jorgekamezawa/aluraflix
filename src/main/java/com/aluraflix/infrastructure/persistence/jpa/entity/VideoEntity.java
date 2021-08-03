package com.aluraflix.infrastructure.persistence.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", length = 30, nullable = false, unique = false)
    private String titulo;

    @Column(name = "descricao", length = 50, nullable = false, unique = false)
    private String descricao;

    @Column(name = "url", length = 50, nullable = false, unique = false)
    private String url;
}
