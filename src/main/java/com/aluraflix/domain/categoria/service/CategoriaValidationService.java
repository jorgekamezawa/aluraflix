package com.aluraflix.domain.categoria.service;

import com.aluraflix.domain.categoria.exception.CategoriaFieldNotAcceptableException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.categoria.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaValidationService {

    private final CategoriaValidarQueIdCategoriaSejaNulo validarQueIdCategoriaSejaNulo;
    private final CategoriaValidarQueCamposNaoSejamNulos validarQueCamposNaoSejamNulos;
    private final CategoriaValidarTamanhoDosCampos validarTamanhoCampos;
    private final CategoriaValidarSeContemPeloMenosUmCampoPreenchido validarSeContemPeloMenosUmCampoPreenchido;
    private final CategoriaValidarParaNaoTerTodosCampoPreenchidos validarParaNaoTerTodosCampoPreenchidos;
    private final CategoriaValidarSeDoisObejtosCategoriaSaoIguais validarSeDoisObejtosCategoriaSaoIguais;

    public void validarCamposCategoriaParaSalvar(Categoria categoria) {
        validarQueIdCategoriaSejaNulo.validar(categoria);
        validarQueCamposNaoSejamNulos.validar(categoria);
        validarTamanhoCampos.validar(categoria);
    }

    public void validarCamposCategoriaParaAlterarCompletamente(Categoria categoria) {
        validarQueCamposNaoSejamNulos.validar(categoria);
        validarTamanhoCampos.validar(categoria);
    }

    public void validarCamposCategoriaParaAlterarParcialmente(Categoria categoria) {
        validarSeContemPeloMenosUmCampoPreenchido.validar(categoria);
        validarParaNaoTerTodosCampoPreenchidos.validar(categoria);
        validarTamanhoCampos.validar(categoria);
    }

    public void validarSeCategoriaDoVideoNaoFoiAlterada(Categoria categoriaCadastrada,
                                                        Categoria categoriaEnviada) {
        validarQueCamposNaoSejamNulos.validar(categoriaEnviada);
        validarTamanhoCampos.validar(categoriaEnviada);
        if (!validarSeDoisObejtosCategoriaSaoIguais.validar(categoriaCadastrada, categoriaEnviada)) {
            throw new CategoriaFieldNotAcceptableException("A categoria nao pode ser alterada atraves do video!");
        }
    }
}
