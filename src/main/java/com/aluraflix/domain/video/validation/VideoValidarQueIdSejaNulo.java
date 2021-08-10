package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoValidarQueIdSejaNulo {

    public void validar(VideoDto videoDto) {
        if (videoDto.getId() != null) {
            throw new VideoFieldNotAcceptableException("Nao pode conter Id para realizar o cadastro de um novo video!");
        }
    }
}
