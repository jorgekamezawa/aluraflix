package com.aluraflix.infrastructure.categoria.persistence.jpa.mapper;

import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.infrastructure.categoria.persistence.jpa.entity.CategoriaPersistenceEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {

    public List<Categoria> converterListaCategoriaEntityParaListaCategoriaModel(List<CategoriaPersistenceEntity> listaEntity) {
        List<Categoria> listaModel = new ArrayList<>();
        listaEntity.forEach(entity -> listaModel.add(converterCategoriaEntityParaCategoriaModel(entity)));

        return listaModel;
    }

    public Categoria converterCategoriaEntityParaCategoriaModel(CategoriaPersistenceEntity entity) {
        return Categoria
                .builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .cor(entity.getCor()).build();
    }

    public CategoriaPersistenceEntity converterCategoriaModelParaCategoriaEntity(Categoria model) {
        return CategoriaPersistenceEntity.builder()
                .id(model.getId())
                .titulo(model.getTitulo())
                .cor(model.getCor()).build();
    }
}
