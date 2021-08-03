package com.aluraflix.domain.service;

import com.aluraflix.domain.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.exception.NotAcceptableException;
import com.aluraflix.domain.model.VideoDto;
import com.aluraflix.domain.validation.VideoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoPersistenceAdapter videoAdapter;
    private final VideoValidation videoValidation;

    public List<VideoDto> buscarTodosVideos() {
        return videoAdapter.buscarTodosVideos();
    }

    public VideoDto buscarVideoPorId(Long idVideo) {
        return videoAdapter.buscarVideoPorId(idVideo);
    }

    public VideoDto cadastrarVideo(VideoDto videoDto) {
//      Certificar que nao contem Id, pois esse metodo é para cadastrar um novo video e nao atualizar
        if (videoDto.getId() != null) {
            throw new NotAcceptableException("Nao pode conter Id para realizar o cadastro de um novo video!");
        }
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaSalvar(videoDto);

        return videoAdapter.salvarVideo(videoDto);
    }

    public VideoDto alterarVideo(Long idVideo, VideoDto videoDto) {
        videoDto.setId(idVideo);

        List<VideoDto> listaVideoDto = buscarTodosVideos();
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaAlterar(videoDto, listaVideoDto);

        return videoAdapter.salvarVideo(videoDto);
    }

    public VideoDto alterarVideoParcialmente(Long idVideo, VideoDto videoDto) {
        videoDto.setId(idVideo);

        List<VideoDto> listaVideoDto = buscarTodosVideos();
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaAlterarParcialmente(videoDto, listaVideoDto);

        return videoAdapter.salvarVideo(incluirDadosDaBaseEmUmVideoDtoIncompleto(videoDto, buscarVideoPorId(idVideo)));
    }

    public void deletarVideo(Long idVideo) {
        videoAdapter.deletarVideoPorId(idVideo);
    }

    private VideoDto incluirDadosDaBaseEmUmVideoDtoIncompleto(VideoDto videoDto, VideoDto videoDtoCadastrado) {
        if (videoDto.getTitulo() == null) videoDto.setTitulo(videoDtoCadastrado.getTitulo());
        if (videoDto.getDescricao() == null) videoDto.setDescricao(videoDtoCadastrado.getDescricao());
        if (videoDto.getUrl() == null) videoDto.setUrl(videoDtoCadastrado.getUrl());

        return videoDto;
    }
}
