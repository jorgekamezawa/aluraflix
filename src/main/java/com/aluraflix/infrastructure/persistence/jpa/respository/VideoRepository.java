package com.aluraflix.infrastructure.persistence.jpa.respository;

import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoPersistenceEntity, Long> {

    List<VideoPersistenceEntity> findAllByCategoria(CategoriaPersistenceEntity categoriaEntity);
}
