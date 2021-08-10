package com.aluraflix.domain.video.service;

import com.aluraflix.domain.video.model.VideoDto;
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

    public void validarCamposVideoParaCadastrar(VideoDto videoDto) {
        validarQueIdSejaNulo.validar(videoDto);
        validarQueCamposNaoSejamNulos.validar(videoDto);
        validarTamanhoCampos.validar(videoDto);
        validarUrl.validar(videoDto.getUrl());
    }

    public void validarCamposVideoParaAlterarCompletamente(VideoDto videoDto) {
        validarQueCamposNaoSejamNulos.validar(videoDto);
        validarTamanhoCampos.validar(videoDto);
        validarUrl.validar(videoDto.getUrl());
    }

    public void validarCamposVideoParaAlterarParcialmente(VideoDto videoDto) {
        validarSeContemPeloMenosUmCampoPreenchido.validar(videoDto);
        validarParaNaoTerTodosCampoPreenchidos.validar(videoDto);
        validarTamanhoCampos.validar(videoDto);
    }
}
