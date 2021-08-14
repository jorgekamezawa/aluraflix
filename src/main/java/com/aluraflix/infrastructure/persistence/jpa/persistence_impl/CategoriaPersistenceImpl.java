package com.aluraflix.infrastructure.persistence.jpa.persistence_impl;

import com.aluraflix.domain.categoria.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.exception.CategoriaNoContentException;
import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.common.model.PageDto;
import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaPersistenceImpl implements CategoriaPersistenceAdapter {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public PageDto<Categoria> buscarTodaListaPaginadaDeCategorias(Pageable paginavel) {
        Page<CategoriaPersistenceEntity> paginaCategoriaEntity = categoriaRepository.findAll(paginavel);

        if (paginaCategoriaEntity.getContent().isEmpty()) {
            throw new CategoriaNoContentException();
        }

        List<Categoria> listaCategorias = categoriaMapper.converterListaCategoriaEntityParaListaCategoriaModel(paginaCategoriaEntity.getContent());

        return new PageDto<>(listaCategorias, paginaCategoriaEntity.getTotalElements(), paginaCategoriaEntity.getTotalPages());
    }

    @Override
    public Categoria buscarCategoriaPorId(Long idCategoria) {
        CategoriaPersistenceEntity categoriaEntity = Optional.ofNullable(categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new CategoriaValueNotFoundException(
                        "Categoria com Id " + idCategoria + " nao encontrado!"))).get();

        return categoriaMapper.converterCategoriaEntityParaCategoriaModel(categoriaEntity);
    }

    @Override
    @Transactional
    public Categoria salvarCategoria(Categoria categoriaModel) {
        CategoriaPersistenceEntity categoriaEntity = categoriaMapper.converterCategoriaModelParaCategoriaEntity(categoriaModel);

        try {
            categoriaEntity = categoriaRepository.save(categoriaEntity);

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao cadastrar categoria!");
        }

        return categoriaMapper.converterCategoriaEntityParaCategoriaModel(categoriaEntity);
    }

    @Override
    @Transactional
    public Categoria alterarCategoria(Categoria categoriaModel) {
        return salvarCategoria(categoriaModel);
    }

    @Override
    @Transactional
    public void deletarCategoriaPorId(Long idCategoria) {
        try {
            Categoria categoriaModel = buscarCategoriaPorId(idCategoria);
            CategoriaPersistenceEntity categoriaEntity = categoriaMapper.converterCategoriaModelParaCategoriaEntity(categoriaModel);

            categoriaRepository.delete(categoriaEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new CategoriaFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar categoria!");
        }
    }
}
