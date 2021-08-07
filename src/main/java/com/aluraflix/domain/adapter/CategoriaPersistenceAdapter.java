package com.aluraflix.domain.adapter;

import com.aluraflix.domain.model.CategoriaDto;

import java.util.List;

public interface CategoriaPersistenceAdapter {

    List<CategoriaDto> buscarTodasCategorias();

    CategoriaDto buscarCategoriaPorId(Long id);

    CategoriaDto salvarCategoria(CategoriaDto categoriaDto);

    CategoriaDto alterarCategoria(CategoriaDto categoriaDto);

    void deletarCategoriaPorId(Long idCategoria);
}
