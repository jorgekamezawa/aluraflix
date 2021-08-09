package com.aluraflix.domain.categoria.service;

import com.aluraflix.domain.categoria.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.categoria.builder.CategoriaBuilder;
import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.exception.CategoriaPadraoException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.video.exception.VideoValueNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaPersistenceAdapter categoriaAdapter;
    private final CategoriaValidationService categoriaValidationService;
    private final CategoriaBuilder categoriaBuilder;

    public List<Categoria> buscarTodasCategorias() {
        return categoriaAdapter.buscarTodasCategorias();
    }

    public Categoria buscarCategoriaPorId(Long idCategoria) {
        return categoriaAdapter.buscarCategoriaPorId(idCategoria);
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        categoriaValidationService.validarCamposCategoriaParaSalvar(categoria);

        return categoriaAdapter.salvarCategoria(categoria);
    }

    public Categoria alterarCategoriaCompleta(Long idCategoria, Categoria categoria) {
        validarCategoriaPorId(idCategoria);

        categoria.setId(idCategoria);
        categoriaValidationService.validarCamposCategoriaParaAlterar(categoria);

        return categoriaAdapter.alterarCategoria(categoria);
    }

    public Categoria alterarCategoriaParcialmente(Long idCategoria, Categoria categoria) {
        Categoria categoriaCadastrada = validarCategoriaPorId(idCategoria);

        categoriaValidationService.validarCamposCategoriaParaAlterarParcialmente(categoria);

        return categoriaAdapter.alterarCategoria(
                categoriaBuilder.alterarCategoriaCadastrada(categoriaCadastrada, categoria));
    }

    public void deletarCategoria(Long idCategoria) {
        categoriaAdapter.deletarCategoriaPorId(idCategoria);
    }

    private Categoria validarCategoriaPorId(Long idCategoria) {
        try {
            return buscarCategoriaPorId(idCategoria);

        } catch (CategoriaValueNotFoundException e) {
            throw new CategoriaFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");
        }
    }

    public Categoria buscarCategoriaPadrao() {
        try {
            return buscarCategoriaPorId(1L);
        } catch (VideoValueNotFoundException e) {
            throw new CategoriaPadraoException("Categoria padrao nao encontrada!");
        }
    }

    public void validarSeCategoriaDoVideoNaoFoiAlterada(Categoria categoria) {
        Categoria categoriaCadastrada = validarCategoriaPorId(categoria.getId());
        categoriaValidationService.validarSeCategoriaDoVideoNaoFoiAlterada(categoriaCadastrada, categoria);
    }
}
