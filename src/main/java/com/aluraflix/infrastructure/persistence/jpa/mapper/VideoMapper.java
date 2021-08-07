package com.aluraflix.infrastructure.persistence.jpa.mapper;

import com.aluraflix.domain.model.VideoDto;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoMapper {

    private final CategoriaMapper categoriaMapper;

    public List<VideoDto> converterListaVideoEntityParaListaVideoDto(List<VideoEntity> listaEntity) {
        List<VideoDto> listaDto = new ArrayList<>();
        listaEntity.forEach(entity -> listaDto.add(converterVideoEntityParaVideoDto(entity)));

        return listaDto;
    }

    public VideoDto converterVideoEntityParaVideoDto(VideoEntity entity) {
        return VideoDto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .url(entity.getUrl())
                .categoria(categoriaMapper.converterCategoriaEntityParaCategoriaDto(entity.getCategoria())).build();
    }

    public VideoEntity converterVideoDtoParaVideoEntity(VideoDto dto) {
        return VideoEntity.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .url(dto.getUrl())
                .categoria(categoriaMapper.converterCategoriaDtoParaCategoriaEntity(dto.getCategoria())).build();
    }
}
