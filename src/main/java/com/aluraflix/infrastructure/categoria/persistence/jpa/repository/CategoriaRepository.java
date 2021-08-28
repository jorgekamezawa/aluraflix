package com.aluraflix.infrastructure.categoria.persistence.jpa.repository;

import com.aluraflix.infrastructure.categoria.persistence.jpa.entity.CategoriaPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaPersistenceEntity, Long> {
}
