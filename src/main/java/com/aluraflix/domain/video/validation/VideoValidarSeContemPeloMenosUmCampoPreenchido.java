package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoValidarSeContemPeloMenosUmCampoPreenchido {

    public void validar(Video video) {
        if (video.getTitulo() != null && video.getDescricao() != null &&
                video.getUrl() != null) {
            throw new VideoFieldNotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
        }
    }
}
