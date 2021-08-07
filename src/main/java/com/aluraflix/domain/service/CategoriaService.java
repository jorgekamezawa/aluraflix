package com.aluraflix.domain.service;

import com.aluraflix.domain.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.builder.CategoriaBuilder;
import com.aluraflix.domain.exception.categoria.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.exception.categoria.CategoriaValueNotFoundException;
import com.aluraflix.domain.exception.categoria.CategoriaPadraoException;
import com.aluraflix.domain.exception.video.VideoValueNotFoundException;
import com.aluraflix.domain.model.CategoriaDto;
import com.aluraflix.domain.validation.CategoriaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaPersistenceAdapter categoriaAdapter;
    private final CategoriaValidation categoriaValidation;
    private final CategoriaBuilder categoriaBuilder;

    public List<CategoriaDto> buscarTodasCategorias() {
        return categoriaAdapter.buscarTodasCategorias();
    }

    public CategoriaDto buscarCategoriaPorId(Long idCategoria) {
        return categoriaAdapter.buscarCategoriaPorId(idCategoria);
    }

    public CategoriaDto cadastrarCategoria(CategoriaDto categoriaDto) {
        categoriaValidation.validarCamposCategoriaParaSalvar(categoriaDto);

        return categoriaAdapter.salvarCategoria(categoriaDto);
    }

    public CategoriaDto alterarCategoriaCompleta(Long idCategoria, CategoriaDto categoriaDto) {
        validarCategoriaPorId(idCategoria);

        categoriaDto.setId(idCategoria);
        categoriaValidation.validarCamposCategoriaParaAlterar(categoriaDto);

        return categoriaAdapter.alterarCategoria(categoriaDto);
    }

    public CategoriaDto alterarCategoriaParcialmente(Long idCategoria, CategoriaDto categoriaDto) {
        CategoriaDto categoriaCadastrada = validarCategoriaPorId(idCategoria);

        categoriaValidation.validarCamposCategoriaParaAlterarParcialmente(categoriaDto);

        return categoriaAdapter.alterarCategoria(
                categoriaBuilder.alterarCategoriaCadastrada(categoriaCadastrada, categoriaDto));
    }

    public void deletarCategoria(Long idCategoria) {
        categoriaAdapter.deletarCategoriaPorId(idCategoria);
    }

    private CategoriaDto validarCategoriaPorId(Long idCategoria) {
        try {
            return buscarCategoriaPorId(idCategoria);

        } catch (CategoriaValueNotFoundException e) {
            throw new CategoriaFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");
        }
    }

    public CategoriaDto buscarCategoriaPadrao() {
        try {
            return buscarCategoriaPorId(1L);
        } catch (VideoValueNotFoundException e) {
            throw new CategoriaPadraoException("Categoria padrao nao encontrada!");
        }
    }

    public void validarSeCategoriaDoVideoNaoFoiAlterada(CategoriaDto categoriaDto) {
        CategoriaDto categoriaCadastrada = validarCategoriaPorId(categoriaDto.getId());
        categoriaValidation.validarSeCategoriaDoVideoNaoFoiAlterada(categoriaCadastrada, categoriaDto);
    }
}
