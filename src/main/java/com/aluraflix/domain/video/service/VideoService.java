package com.aluraflix.domain.video.service;

import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.service.CategoriaService;
import com.aluraflix.domain.common.model.PageDto;
import com.aluraflix.domain.video.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.video.builder.VideoBuilder;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoPersistenceAdapter videoAdapter;
    private final VideoValidationService videoValidationService;
    private final VideoBuilder videoBuilder;
    private final CategoriaService categoriaService;

    public PageDto<Video> buscarListaPaginadaDeVideosPorFiltro(String titulo, Pageable paginavel) {
        if (titulo != null) {
            return videoAdapter.buscarListaPaginadaVideoPorTitulo(titulo, paginavel);
        }
        return videoAdapter.buscarTodaListaPaginadaDeVideos(paginavel);
    }

    public Video buscarVideoPorId(Long idVideo) {
        return videoAdapter.buscarVideoPorId(idVideo);
    }

    public Video cadastrarVideo(Video video) {
        if (video.getCategoria() == null) {
            video.setCategoria(categoriaService.buscarCategoriaPadrao());
        }

        videoValidationService.validarCamposVideoParaCadastrar(video);
        categoriaService.validarSeCategoriaDoVideoNaoFoiAlterada(video.getCategoria());

        return videoAdapter.salvarVideo(video);
    }

    public Video alterarVideoCompletamente(Long idVideo, Video video) {
        validarVideoPorId(idVideo);

        video.setId(idVideo);
        videoValidationService.validarCamposVideoParaAlterarCompletamente(video);

        return videoAdapter.alterarVideo(video);
    }

    public Video alterarVideoParcialmente(Long idVideo, Video video) {
        Video videoCadastrado = validarVideoPorId(idVideo);

        videoValidationService.validarCamposVideoParaAlterarParcialmente(video);

        return videoAdapter.alterarVideo(
                videoBuilder.alterarVideoCadastrado(videoCadastrado, video));
    }

    public void deletarVideo(Long idVideo) {
        videoAdapter.deletarVideoPorId(idVideo);
    }

    private Video validarVideoPorId(Long idCategoria) {
        try {
            return buscarVideoPorId(idCategoria);

        } catch (CategoriaValueNotFoundException e) {
            throw new VideoFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");
        }
    }

    public PageDto<Video> buscarListaPaginadaDeVideosPorCategoria(Long idCateogria, Pageable paginavel) {
        return videoAdapter.buscarListaPaginadaDeVideosPorCategoria(categoriaService.buscarCategoriaPorId(idCateogria), paginavel);
    }
}
