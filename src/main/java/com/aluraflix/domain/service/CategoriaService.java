package com.aluraflix.domain.service;

import com.aluraflix.domain.adapter.CategoriaPersistenceAdapter;
import com.aluraflix.domain.exception.NotAcceptableException;
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

    public List<CategoriaDto> buscarTodasCategorias() {

        return categoriaAdapter.buscarTodasCategorias();
    }

    public CategoriaDto buscarCategoriaPorId(Long idCategoria) {
        return categoriaAdapter.buscarCategoriaPorId(idCategoria);
    }

    public CategoriaDto cadastrarCategoria(CategoriaDto CategoriaDto) {
//      Certificar que nao contem Id, pois esse metodo é para cadastrar uma nova categoria e nao atualizar
        if (CategoriaDto.getId() != null) {
            throw new NotAcceptableException("Nao pode conter Id para realizar o cadastro de uma nova categoria!");
        }
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaSalvar(CategoriaDto);

        return categoriaAdapter.salvarCategoria(CategoriaDto);
    }

    public CategoriaDto alterarCategoria(Long idCategoria, CategoriaDto categoriaDto) {
        categoriaDto.setId(idCategoria);

        List<CategoriaDto> listaCategoriaDto = buscarTodasCategorias();
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaAlterar(categoriaDto, listaCategoriaDto);

        return categoriaAdapter.salvarCategoria(categoriaDto);
    }

    public CategoriaDto alterarCategoriaParcialmente(Long idCategoria, CategoriaDto categoriaDto) {
        categoriaDto.setId(idCategoria);

        List<CategoriaDto> listaCategoriaDto = buscarTodasCategorias();
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaAlterarParcialmente(categoriaDto, listaCategoriaDto);

        return categoriaAdapter.salvarCategoria(
                incluirDadosDaBaseEmUmaCategoriaDtoIncompleta(categoriaDto, buscarCategoriaPorId(idCategoria)));
    }

    public void deletarCategoria(Long idCategoria) {
        categoriaAdapter.deletarCategoriaPorId(idCategoria);
    }

    private CategoriaDto incluirDadosDaBaseEmUmaCategoriaDtoIncompleta(CategoriaDto categoriaDto, CategoriaDto categoriaDtoCadastrado) {
        if (categoriaDto.getTitulo() == null) categoriaDto.setTitulo(categoriaDtoCadastrado.getTitulo());
        if (categoriaDto.getCor() == null) categoriaDto.setCor(categoriaDtoCadastrado.getCor());

        return categoriaDto;
    }
}
