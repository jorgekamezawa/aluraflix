package com.aluraflix.domain.categoria.validation;

import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaValidarQueCamposNaoSejamNulos {

    public void validar(Categoria categoria) {
        List<String> erros = new ArrayList<>();

        if (categoria.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (categoria.getCor() == null) {
            erros.add("O campo cor nao pode ser nulo");
        }

        if (!erros.isEmpty()) {
            throw new CategoriaFieldNotAcceptableException("Contem campos nulos", erros);
        }
    }
}
