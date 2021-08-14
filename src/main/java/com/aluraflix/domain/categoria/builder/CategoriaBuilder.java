package com.aluraflix.domain.categoria.builder;

import com.aluraflix.domain.categoria.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaBuilder {

    public Categoria alterarCategoriaCadastrada(Categoria categoriaCadastrado, Categoria categoria) {
        if (categoria.getTitulo() != null) categoriaCadastrado.setTitulo(categoria.getTitulo());
        if (categoria.getCor() != null) categoriaCadastrado.setCor(categoria.getCor());

        return categoriaCadastrado;
    }
}
