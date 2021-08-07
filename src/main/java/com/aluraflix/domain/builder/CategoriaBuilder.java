package com.aluraflix.domain.builder;

import com.aluraflix.domain.model.CategoriaDto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaBuilder {

    public CategoriaDto alterarCategoriaCadastrada(CategoriaDto categoriaDtoCadastrado, CategoriaDto categoriaDto) {
        if (categoriaDto.getTitulo() == null) categoriaDtoCadastrado.setTitulo(categoriaDto.getTitulo());
        if (categoriaDto.getCor() == null) categoriaDtoCadastrado.setCor(categoriaDto.getCor());

        return categoriaDtoCadastrado;
    }
}
