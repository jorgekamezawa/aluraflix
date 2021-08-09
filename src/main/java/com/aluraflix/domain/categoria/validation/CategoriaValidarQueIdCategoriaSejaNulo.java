package com.aluraflix.domain.categoria.validation;

import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.model.Categoria;

public class CategoriaValidarQueIdCategoriaSejaNulo {

    public void validar(Categoria categoria) {
        if (categoria.getId() != null) {
            throw new CategoriaFieldNotAcceptableException("Nao pode conter Id para realizar o cadastro de uma nova categoria!");
        }
    }
}
