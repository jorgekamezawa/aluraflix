package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidarQueCamposNaoSejamNulos {

    public void validar(Video video) {
        List<String> erros = new ArrayList<>();

        if (video.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (video.getDescricao() == null) {
            erros.add("O campo descricao nao pode ser nulo");
        }
        if (video.getUrl() == null) {
            erros.add("O campo url nao pode ser nulo");
        }
        if (video.getCategoria() == null) {
            erros.add("A categoria nao pode ser nula");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos nulos", erros);
        }
    }
}
