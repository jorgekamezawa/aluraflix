package com.aluraflix.mapper;

import com.aluraflix.entity.CategoriaEntity;
import com.aluraflix.model.CategoriaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {

    public List<CategoriaDto> converterListaCategoriaEntityParaListaCategoriaDto(List<CategoriaEntity> listaEntity) {
        List<CategoriaDto> listaDto = new ArrayList<>();
        listaEntity.forEach(entity -> listaDto.add(converterCategoriaEntityParaCategoriaDto(entity)));

        return listaDto;
    }

    public CategoriaDto converterCategoriaEntityParaCategoriaDto(CategoriaEntity entity) {
        return CategoriaDto
                .builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .cor(entity.getCor()).build();
    }

    public CategoriaEntity converterCategoriaDtoParaCategoriaEntity(CategoriaDto dto) {
        return CategoriaEntity.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .cor(dto.getCor()).build();
    }
}
