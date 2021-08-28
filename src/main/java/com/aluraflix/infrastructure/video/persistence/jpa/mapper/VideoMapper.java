package com.aluraflix.infrastructure.video.persistence.jpa.mapper;

import com.aluraflix.domain.video.model.Video;
import com.aluraflix.infrastructure.categoria.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.video.persistence.jpa.entity.VideoPersistenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoMapper {

    private final CategoriaMapper categoriaMapper;

    public List<Video> converterListaVideoEntityParaListaVideoModel(List<VideoPersistenceEntity> listaEntity) {
        List<Video> listaModel = new ArrayList<>();
        listaEntity.forEach(entity -> listaModel.add(converterVideoEntityParaVideoModel(entity)));

        return listaModel;
    }

    public Video converterVideoEntityParaVideoModel(VideoPersistenceEntity entity) {
        return Video.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .url(entity.getUrl())
                .categoria(categoriaMapper.converterCategoriaEntityParaCategoriaModel(entity.getCategoria())).build();
    }

    public VideoPersistenceEntity converterVideoModelParaVideoEntity(Video model) {
        return VideoPersistenceEntity.builder()
                .id(model.getId())
                .titulo(model.getTitulo())
                .descricao(model.getDescricao())
                .url(model.getUrl())
                .categoria(categoriaMapper.converterCategoriaModelParaCategoriaEntity(model.getCategoria())).build();
    }
}
