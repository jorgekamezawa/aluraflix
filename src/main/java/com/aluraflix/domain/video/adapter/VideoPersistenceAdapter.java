package com.aluraflix.domain.video.adapter;

import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.video.model.Video;

import java.util.List;

public interface VideoPersistenceAdapter {

    List<Video> buscarTodosVideos();

    Video buscarVideoPorId(Long id);

    Video salvarVideo(Video video);

    Video alterarVideo(Video video);

    void deletarVideoPorId(Long idVideo);

    List<Video> buscarVideoPorCategoria(Categoria categoria);
}
