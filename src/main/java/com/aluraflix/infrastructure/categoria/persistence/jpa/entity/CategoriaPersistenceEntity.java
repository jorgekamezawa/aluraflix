package com.aluraflix.infrastructure.categoria.persistence.jpa.entity;

import com.aluraflix.infrastructure.video.persistence.jpa.entity.VideoPersistenceEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class CategoriaPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", length = 30, nullable = false)
    private String titulo;

    @Column(name = "cor", length = 20, nullable = false)
    private String cor;

    @OneToMany(mappedBy = "categoria", targetEntity = VideoPersistenceEntity.class, cascade = CascadeType.MERGE)
    private List<VideoPersistenceEntity> videos;
}
