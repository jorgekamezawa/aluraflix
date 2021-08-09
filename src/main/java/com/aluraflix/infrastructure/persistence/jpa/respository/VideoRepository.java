package com.aluraflix.infrastructure.persistence.jpa.respository;

import com.aluraflix.infrastructure.persistence.jpa.entity.VideoPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoPersistenceEntity, Long> {
}
