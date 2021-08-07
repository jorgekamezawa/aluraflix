package com.aluraflix.domain.validation;

import com.aluraflix.domain.exception.categoria.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.model.CategoriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriaValidation {

    public void validarCamposCategoriaParaSalvar(CategoriaDto categoriaDto) {
        validarQueIdCategoriaSejaNulo(categoriaDto);
        validarQueCamposNaoSejamNulos(categoriaDto);
        validarTamanhoCampos(categoriaDto);
    }

    public void validarCamposCategoriaParaAlterar(CategoriaDto categoriaDto) {
        validarQueCamposNaoSejamNulos(categoriaDto);
        validarTamanhoCampos(categoriaDto);
    }

    public void validarCamposCategoriaParaAlterarParcialmente(CategoriaDto categoriaDto) {
        validarSeContemPeloMenosUmCampoPreenchido(categoriaDto);
        validarParaNaoTerTodosCampoPreenchidos(categoriaDto);
        validarTamanhoCampos(categoriaDto);
    }

    private void validarQueIdCategoriaSejaNulo(CategoriaDto categoriaDto) {
        if (categoriaDto.getId() != null) {
            throw new CategoriaFieldNotAcceptableException("Nao pode conter Id para realizar o cadastro de uma nova categoria!");
        }
    }

    public void validarQueCamposNaoSejamNulos(CategoriaDto categoriaDto) {
        List<String> erros = new ArrayList<>();

        if (categoriaDto.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (categoriaDto.getCor() == null) {
            erros.add("O campo cor nao pode ser nulo");
        }

        if (!erros.isEmpty()) {
            throw new CategoriaFieldNotAcceptableException("Contem campos nulos", erros);
        }
    }

    public void validarTamanhoCampos(CategoriaDto categoriaDto) {
        List<String> erros = new ArrayList<>();

        int tamanho20 = 20;
        int tamanho30 = 30;

        if (categoriaDto.getTitulo() != null && categoriaDto.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (categoriaDto.getCor() != null && categoriaDto.getCor().length() > tamanho20) {
            erros.add("O campo cor nao pode ter mais que " + tamanho20 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new CategoriaFieldNotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }

    private void validarSeContemPeloMenosUmCampoPreenchido(CategoriaDto categoriaDto) {
        if (categoriaDto.getTitulo() == null && categoriaDto.getCor() == null) {
            throw new CategoriaFieldNotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }

    private void validarParaNaoTerTodosCampoPreenchidos(CategoriaDto categoriaDto) {
        if (categoriaDto.getTitulo() != null && categoriaDto.getCor() != null) {
            throw new CategoriaFieldNotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
        }
    }

    public void validarSeCategoriaDoVideoNaoFoiAlterada(CategoriaDto categoriaCadastrada,
                                                        CategoriaDto categoriaEnviada) {
        validarQueCamposNaoSejamNulos(categoriaEnviada);
        validarTamanhoCampos(categoriaEnviada);

        if (!categoriaEnviada.getCor().equals(categoriaCadastrada.getCor()) ||
                !categoriaEnviada.getTitulo().equals(categoriaCadastrada.getTitulo())) {
            throw new CategoriaFieldNotAcceptableException("Categoria nao pode ser alterada");
        }
    }
}
