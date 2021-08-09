package com.aluraflix.infrastructure.persistence.jpa.entity;

import lombok.*;

import javax.persistence.*;

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
}
