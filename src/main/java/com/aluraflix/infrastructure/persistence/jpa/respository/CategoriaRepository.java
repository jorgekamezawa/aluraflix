package com.aluraflix.infrastructure.persistence.jpa.respository;

import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaPersistenceEntity, Long> {
}
