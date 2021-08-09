package com.aluraflix.infrastructure.persistence.jpa.mapper;

import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.domain.categoria.model.Categoria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {

    public List<Categoria> converterListaCategoriaEntityParaListaCategoriaDto(List<CategoriaPersistenceEntity> listaEntity) {
        List<Categoria> listaDto = new ArrayList<>();
        listaEntity.forEach(entity -> listaDto.add(converterCategoriaEntityParaCategoriaDto(entity)));

        return listaDto;
    }

    public Categoria converterCategoriaEntityParaCategoriaDto(CategoriaPersistenceEntity entity) {
        return Categoria
                .builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .cor(entity.getCor()).build();
    }

    public CategoriaPersistenceEntity converterCategoriaDtoParaCategoriaEntity(Categoria dto) {
        return CategoriaPersistenceEntity.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .cor(dto.getCor()).build();
    }
}
