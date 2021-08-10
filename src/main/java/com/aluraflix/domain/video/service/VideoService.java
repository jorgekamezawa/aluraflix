package com.aluraflix.domain.video.service;

import com.aluraflix.domain.categoria.service.CategoriaService;
import com.aluraflix.domain.video.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.video.builder.VideoBuilder;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.model.VideoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoPersistenceAdapter videoAdapter;
    private final VideoValidationService videoValidationService;
    private final VideoBuilder videoBuilder;
    private final CategoriaService categoriaService;

    public List<VideoDto> buscarTodosVideos() {
        return videoAdapter.buscarTodosVideos();
    }

    public VideoDto buscarVideoPorId(Long idVideo) {
        return videoAdapter.buscarVideoPorId(idVideo);
    }

    public VideoDto cadastrarVideo(VideoDto videoDto) {
        if (videoDto.getCategoria() == null) {
            videoDto.setCategoria(categoriaService.buscarCategoriaPadrao());
        }

        videoValidationService.validarCamposVideoParaCadastrar(videoDto);
        categoriaService.validarSeCategoriaDoVideoNaoFoiAlterada(videoDto.getCategoria());

        return videoAdapter.salvarVideo(videoDto);
    }

    public VideoDto alterarVideoCompletamente(Long idVideo, VideoDto videoDto) {
        validarVideoPorId(idVideo);

        videoDto.setId(idVideo);
        videoValidationService.validarCamposVideoParaAlterarCompletamente(videoDto);

        return videoAdapter.alterarVideo(videoDto);
    }

    public VideoDto alterarVideoParcialmente(Long idVideo, VideoDto videoDto) {
        VideoDto videoCadastrado = validarVideoPorId(idVideo);

        videoValidationService.validarCamposVideoParaAlterarParcialmente(videoDto);

        return videoAdapter.alterarVideo(
                videoBuilder.alterarVideoCadastrado(videoCadastrado, videoDto));
    }

    public void deletarVideo(Long idVideo) {
        videoAdapter.deletarVideoPorId(idVideo);
    }

    private VideoDto validarVideoPorId(Long idCategoria) {
        try {
            return buscarVideoPorId(idCategoria);

        } catch (CategoriaValueNotFoundException e) {
            throw new VideoFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");
        }
    }
}
