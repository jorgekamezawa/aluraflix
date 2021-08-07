package com.aluraflix.domain.builder;

import com.aluraflix.domain.model.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoBuilder {

    public VideoDto alterarVideoCadastrado(VideoDto videoDtoCadastrado, VideoDto videoDto) {
        if (videoDto.getTitulo() == null) videoDtoCadastrado.setTitulo(videoDto.getTitulo());
        if (videoDto.getDescricao() == null) videoDtoCadastrado.setDescricao(videoDto.getDescricao());
        if (videoDto.getUrl() == null) videoDtoCadastrado.setUrl(videoDto.getUrl());
//        if (videoDto.getCategoriaId()== null) videoDtoCadastrado.setCategoriaId(videoDto.getCategoriaId());

        return videoDtoCadastrado;
    }
}
