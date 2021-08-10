package com.aluraflix.domain.categoria.validation;

import com.aluraflix.domain.categoria.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidarSeDoisObejtosCategoriaSaoIguais {

    public boolean validar(Categoria categoriaCadastrada,
                           Categoria categoriaEnviada) {
        return categoriaEnviada.getCor().equals(categoriaCadastrada.getCor()) &&
                categoriaEnviada.getTitulo().equals(categoriaCadastrada.getTitulo());
    }
}
