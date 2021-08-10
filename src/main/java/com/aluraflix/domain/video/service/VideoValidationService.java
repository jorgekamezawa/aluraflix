package com.aluraflix.domain.video.service;

import com.aluraflix.domain.video.model.Video;
import com.aluraflix.domain.video.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoValidationService {

    private final VideoValidarQueIdSejaNulo validarQueIdSejaNulo;
    private final VideoValidarQueCamposNaoSejamNulos validarQueCamposNaoSejamNulos;
    private final VideoValidarTamanhoCampos validarTamanhoCampos;
    private final VideoValidarUrl validarUrl;
    private final VideoValidarSeContemPeloMenosUmCampoPreenchido validarSeContemPeloMenosUmCampoPreenchido;
    private final VideoValidarParaNaoTerTodosCampoPreenchidos validarParaNaoTerTodosCampoPreenchidos;

    public void validarCamposVideoParaCadastrar(Video video) {
        validarQueIdSejaNulo.validar(video);
        validarQueCamposNaoSejamNulos.validar(video);
        validarTamanhoCampos.validar(video);
        validarUrl.validar(video.getUrl());
    }

    public void validarCamposVideoParaAlterarCompletamente(Video video) {
        validarQueCamposNaoSejamNulos.validar(video);
        validarTamanhoCampos.validar(video);
        validarUrl.validar(video.getUrl());
    }

    public void validarCamposVideoParaAlterarParcialmente(Video video) {
        validarSeContemPeloMenosUmCampoPreenchido.validar(video);
        validarParaNaoTerTodosCampoPreenchidos.validar(video);
        validarTamanhoCampos.validar(video);
    }
}
