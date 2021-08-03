package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.model.CategoriaDto;
import com.aluraflix.domain.exception.InternalServerErrorException;
import com.aluraflix.domain.exception.NoContentException;
import com.aluraflix.domain.exception.NotAcceptableException;
import com.aluraflix.domain.exception.OkException;
import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaPersistenceAdapterImpl implements CategoriaPersistenceAdapter {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDto> buscarTodasCategorias() {
        List<CategoriaEntity> listaCategoriaEntity = categoriaRepository.findAll();

        if (listaCategoriaEntity.isEmpty()) {
            throw new NoContentException();
        }

        return categoriaMapper.converterListaCategoriaEntityParaListaCategoriaDto(listaCategoriaEntity);
    }

    @Override
    public CategoriaDto buscarCategoriaPorId(Long id) {
        CategoriaEntity categoriaEntity = Optional.ofNullable(categoriaRepository.findById(id)
                .orElseThrow(() -> new OkException("Video com Id " + id + " nao encontrado!"))).get();

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    @Override
    public CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

        try {
            categoriaEntity = categoriaRepository.save(categoriaEntity);
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao cadastrar categoria!");
        }

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    @Override
    public void deletarCategoriaPorId(Long idCategoria) {
        try {
            CategoriaDto categoriaDto = buscarCategoriaPorId(idCategoria);

            CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

            categoriaRepository.delete(categoriaEntity);
        } catch (OkException e) {
            throw new NotAcceptableException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao deletar categoria!");
        }
    }
}
