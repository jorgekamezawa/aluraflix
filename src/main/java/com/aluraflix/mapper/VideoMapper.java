package com.aluraflix.mapper;

import com.aluraflix.entity.VideoEntity;
import com.aluraflix.model.VideoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoMapper {

    public List<VideoDto> converterListaVideoEntityParaListaVideoDto(List<VideoEntity> listaEntity) {
        List<VideoDto> listaDto = new ArrayList<>();
        listaEntity.forEach(entity ->
                listaDto.add(converterVideoEntityParaVideoDto(entity))
        );
        return listaDto;
    }

    public VideoDto converterVideoEntityParaVideoDto(VideoEntity entity){
        return VideoDto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .url(entity.getUrl()).build();
    }
}
