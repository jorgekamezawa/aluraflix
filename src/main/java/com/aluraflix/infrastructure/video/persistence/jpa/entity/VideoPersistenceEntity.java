package com.aluraflix.infrastructure.video.persistence.jpa.entity;

import com.aluraflix.infrastructure.categoria.persistence.jpa.entity.CategoriaPersistenceEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video")
public class VideoPersistenceEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaPersistenceEntity categoria;
}
