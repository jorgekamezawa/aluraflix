package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.categoria.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.exception.CategoriaNoContentException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
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
    public List<Categoria> buscarTodasCategorias() {
        List<CategoriaPersistenceEntity> listaCategoriaPersistenceEntity = categoriaRepository.findAll();

        if (listaCategoriaPersistenceEntity.isEmpty()) {
            throw new CategoriaNoContentException();
        }

        return categoriaMapper.converterListaCategoriaEntityParaListaCategoriaDto(listaCategoriaPersistenceEntity);
    }

    @Override
    public Categoria buscarCategoriaPorId(Long idCategoria) {
        CategoriaPersistenceEntity categoriaPersistenceEntity = Optional.ofNullable(categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new CategoriaValueNotFoundException(
                        "Categoria com Id " + idCategoria + " nao encontrado!"))).get();

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaPersistenceEntity);
    }

    @Override
    @Transactional
    public Categoria salvarCategoria(Categoria categoria) {
        CategoriaPersistenceEntity categoriaPersistenceEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoria);

        try {
            categoriaPersistenceEntity = categoriaRepository.save(categoriaPersistenceEntity);

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao cadastrar categoria!");
        }

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaPersistenceEntity);
    }

    @Override
    @Transactional
    public Categoria alterarCategoria(Categoria categoria) {
        return salvarCategoria(categoria);
    }

    @Override
    @Transactional
    public void deletarCategoriaPorId(Long idCategoria) {
        try {
            Categoria categoria = buscarCategoriaPorId(idCategoria);
            CategoriaPersistenceEntity categoriaPersistenceEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoria);

            categoriaRepository.delete(categoriaPersistenceEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new CategoriaFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar categoria!");
        }
    }
}
