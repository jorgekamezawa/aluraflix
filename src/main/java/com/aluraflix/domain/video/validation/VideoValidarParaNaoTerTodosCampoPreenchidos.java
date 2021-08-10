package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoValidarParaNaoTerTodosCampoPreenchidos {

    public void validar(Video video) {
        if (video.getTitulo() == null && video.getDescricao() == null &&
                video.getUrl() == null) {
            throw new VideoFieldNotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }
}
