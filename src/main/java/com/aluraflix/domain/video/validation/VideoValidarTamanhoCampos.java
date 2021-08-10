package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidarTamanhoCampos {

    public void validar(Video video) {
        List<String> erros = new ArrayList<>();

        int tamanho30 = 30;
        int tamanho50 = 50;

        if (video.getTitulo() != null && video.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (video.getDescricao() != null && video.getDescricao().length() > tamanho50) {
            erros.add("O campo descricao nao pode ter mais que " + tamanho50 + " caracteres");
        }
        if (video.getUrl() != null && video.getUrl().length() > tamanho50) {
            erros.add("O campo url nao pode ter mais que " + tamanho50 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }
}
