package com.aluraflix.domain.categoria.adapter;

import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.common.model.PageDto;
import org.springframework.data.domain.Pageable;

public interface CategoriaPersistenceAdapter {

    PageDto<Categoria> buscarTodaListaPaginadaDeCategorias(Pageable paginavel);

    Categoria buscarCategoriaPorId(Long id);

    Categoria salvarCategoria(Categoria categoria);

    Categoria alterarCategoria(Categoria categoria);

    void deletarCategoriaPorId(Long idCategoria);
}
