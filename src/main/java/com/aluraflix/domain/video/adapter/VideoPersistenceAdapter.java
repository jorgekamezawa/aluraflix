package com.aluraflix.domain.video.adapter;

import com.aluraflix.domain.video.model.VideoDto;

import java.util.List;

public interface VideoPersistenceAdapter {

    List<VideoDto> buscarTodosVideos();

    VideoDto buscarVideoPorId(Long id);

    VideoDto salvarVideo(VideoDto videoDto);

    VideoDto alterarVideo(VideoDto videoDto);

    void deletarVideoPorId(Long idVideo);
}