package com.aluraflix.domain.adapter;

import com.aluraflix.domain.model.VideoDto;

import java.util.List;

public interface VideoPersistenceAdapter {

    List<VideoDto> buscarTodosVideos();

    VideoDto buscarVideoPorId(Long id);

    void deletarVideoPorId(Long idVideo);

    VideoDto salvarVideo(VideoDto videoDto);
}
