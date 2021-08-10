package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.VideoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidarQueCamposNaoSejamNulos {

    public void validar(VideoDto videoDto) {
        List<String> erros = new ArrayList<>();

        if (videoDto.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (videoDto.getDescricao() == null) {
            erros.add("O campo descricao nao pode ser nulo");
        }
        if (videoDto.getUrl() == null) {
            erros.add("O campo url nao pode ser nulo");
        }
        if (videoDto.getCategoria() == null) {
            erros.add("A categoria nao pode ser nula");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos nulos", erros);
        }
    }
}
