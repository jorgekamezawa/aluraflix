package com.aluraflix.domain.adapter;

import com.aluraflix.domain.model.CategoriaDto;

import java.util.List;

public interface CategoriaPersistenceAdapter {

    List<CategoriaDto> buscarTodasCategorias();

    CategoriaDto buscarCategoriaPorId(Long id);

    void deletarCategoriaPorId(Long idCategoria);

    CategoriaDto salvarCategoria(CategoriaDto categoriaDto);
}
