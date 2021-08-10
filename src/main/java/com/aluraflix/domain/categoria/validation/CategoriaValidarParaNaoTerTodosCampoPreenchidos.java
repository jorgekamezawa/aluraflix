package com.aluraflix.domain.categoria.validation;

import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidarParaNaoTerTodosCampoPreenchidos {

    public void validar(Categoria categoria) {
        if (categoria.getTitulo() != null && categoria.getCor() != null) {
            throw new CategoriaFieldNotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
        }
    }
}
