package com.aluraflix.infrastructure.video.persistence.jpa.repository;

import com.aluraflix.infrastructure.categoria.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.video.persistence.jpa.entity.VideoPersistenceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoPersistenceEntity, Long> {

    Page<VideoPersistenceEntity> findAllByCategoria(CategoriaPersistenceEntity categoriaEntity, Pageable page);

    Page<VideoPersistenceEntity> findAllByTituloContaining(String titulo, Pageable page);
}
