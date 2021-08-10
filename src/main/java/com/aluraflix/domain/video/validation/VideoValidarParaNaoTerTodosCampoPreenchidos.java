package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoValidarParaNaoTerTodosCampoPreenchidos {

    public void validar(VideoDto videoDto) {
        if (videoDto.getTitulo() == null && videoDto.getDescricao() == null &&
                videoDto.getUrl() == null) {
            throw new VideoFieldNotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }
}
