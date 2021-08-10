package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.VideoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidarTamanhoCampos {

    public void validar(VideoDto videoDto) {
        List<String> erros = new ArrayList<>();

        int tamanho30 = 30;
        int tamanho50 = 50;

        if (videoDto.getTitulo() != null && videoDto.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (videoDto.getDescricao() != null && videoDto.getDescricao().length() > tamanho50) {
            erros.add("O campo descricao nao pode ter mais que " + tamanho50 + " caracteres");
        }
        if (videoDto.getUrl() != null && videoDto.getUrl().length() > tamanho50) {
            erros.add("O campo url nao pode ter mais que " + tamanho50 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }
}
