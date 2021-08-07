package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.exception.categoria.CategoriaPersistenceException;
import com.aluraflix.domain.exception.categoria.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.exception.categoria.CategoriaNoContentException;
import com.aluraflix.domain.exception.categoria.CategoriaValueNotFoundException;
import com.aluraflix.domain.model.CategoriaDto;
import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new CategoriaNoContentException();
        }

        return categoriaMapper.converterListaCategoriaEntityParaListaCategoriaDto(listaCategoriaEntity);
    }

    @Override
    public CategoriaDto buscarCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoriaEntity = Optional.ofNullable(categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new CategoriaValueNotFoundException(
                        "Categoria com Id " + idCategoria + " nao encontrado!"))).get();

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    @Override
    @Transactional
    public CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

        try {
            categoriaEntity = categoriaRepository.save(categoriaEntity);

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao cadastrar categoria!");
        }

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    @Override
    @Transactional
    public CategoriaDto alterarCategoria(CategoriaDto categoriaDto) {
        return salvarCategoria(categoriaDto);
    }

    @Override
    @Transactional
    public void deletarCategoriaPorId(Long idCategoria) {
        try {
            CategoriaDto categoriaDto = buscarCategoriaPorId(idCategoria);
            CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

            categoriaRepository.delete(categoriaEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new CategoriaFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar categoria!");
        }
    }
}
