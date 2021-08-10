package com.aluraflix.domain.categoria.validation;

import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.model.Categoria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaValidarTamanhoDosCampos {

    public void validar(Categoria categoria) {
        List<String> erros = new ArrayList<>();

        int tamanho20 = 20;
        int tamanho30 = 30;

        if (categoria.getTitulo() != null && categoria.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (categoria.getCor() != null && categoria.getCor().length() > tamanho20) {
            erros.add("O campo cor nao pode ter mais que " + tamanho20 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new CategoriaFieldNotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }
}
