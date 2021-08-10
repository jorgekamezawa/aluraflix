package com.aluraflix.domain.video.builder;

import com.aluraflix.domain.video.model.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoBuilder {

    public Video alterarVideoCadastrado(Video videoCadastrado, Video video) {
        if (video.getTitulo() == null) videoCadastrado.setTitulo(video.getTitulo());
        if (video.getDescricao() == null) videoCadastrado.setDescricao(video.getDescricao());
        if (video.getUrl() == null) videoCadastrado.setUrl(video.getUrl());
//        if (videoDto.getCategoriaId()== null) videoDtoCadastrado.setCategoriaId(videoDto.getCategoriaId());

        return videoCadastrado;
    }
}
