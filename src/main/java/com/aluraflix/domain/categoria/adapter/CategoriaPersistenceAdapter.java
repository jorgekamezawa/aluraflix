package com.aluraflix.domain.categoria.adapter;

import com.aluraflix.domain.categoria.model.Categoria;

import java.util.List;

public interface CategoriaPersistenceAdapter {

    List<Categoria> buscarTodasCategorias();

    Categoria buscarCategoriaPorId(Long id);

    Categoria salvarCategoria(Categoria categoria);

    Categoria alterarCategoria(Categoria categoria);

    void deletarCategoriaPorId(Long idCategoria);
}
