package com.aluraflix.domain.validation;

import com.aluraflix.domain.exception.NotAcceptableException;
import com.aluraflix.domain.model.CategoriaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaValidation {

    public void validarCamposCategoriaParaSalvar(CategoriaDto categoriaDto) {
        validarCamposNulos(categoriaDto);
        validarTamanhoCampos(categoriaDto);
    }

    public void validarCamposCategoriaParaAlterar(CategoriaDto categoriaDto, List<CategoriaDto> listCategoriaDto) {
        validarId(categoriaDto, listCategoriaDto);
        validarCamposCategoriaParaSalvar(categoriaDto);
    }

    public void validarCamposCategoriaParaAlterarParcialmente(CategoriaDto categoriaDto, List<CategoriaDto> listCategoriaDto) {
        validarId(categoriaDto, listCategoriaDto);
        validarSeContemPeloMenosUmCampoPreenchido(categoriaDto);
        validarParaNaoTerTodosCampoPreenchidos(categoriaDto);
        validarTamanhoCampos(categoriaDto);
    }

    public void validarCamposNulos(CategoriaDto categoriaDto) {
        List<String> erros = new ArrayList<>();

        if (categoriaDto.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (categoriaDto.getCor() == null) {
            erros.add("O campo cor nao pode ser nulo");
        }
        if (!erros.isEmpty()) {
            throw new NotAcceptableException("Contem campos nulos", erros);
        }
    }

    private void validarSeContemPeloMenosUmCampoPreenchido(CategoriaDto categoriaDto) {
        if (categoriaDto.getTitulo() == null && categoriaDto.getCor() == null) {
            throw new NotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }

    private void validarParaNaoTerTodosCampoPreenchidos(CategoriaDto categoriaDto) {
        if (categoriaDto.getTitulo() != null && categoriaDto.getCor() != null) {
            throw new NotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
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
            throw new NotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }

    private void validarId(CategoriaDto categoriaDto, List<CategoriaDto> listCategoriaDto) {
        if (categoriaDto.getId() == null) {
            return;
        }
        if (listCategoriaDto.stream().noneMatch(categoriaEntity -> categoriaEntity.getId().equals(categoriaDto.getId()))) {
            throw new NotAcceptableException("Id nao encontrado! Favor enviar Id existente!");
        }
    }
}
