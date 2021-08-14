package com.aluraflix.domain.video.adapter;

import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.common.model.PageDto;
import com.aluraflix.domain.video.model.Video;
import org.springframework.data.domain.Pageable;

public interface VideoPersistenceAdapter {

    PageDto<Video> buscarTodaListaPaginadaDeVideos(Pageable paginavel);

    Video buscarVideoPorId(Long id);

    Video salvarVideo(Video video);

    Video alterarVideo(Video video);

    void deletarVideoPorId(Long idVideo);

    PageDto<Video> buscarListaPaginadaDeVideosPorCategoria(Categoria categoria, Pageable paginavel);

    PageDto<Video> buscarListaPaginadaVideoPorTitulo(String tituloVideo, Pageable paginavel);
}
