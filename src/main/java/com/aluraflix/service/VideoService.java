package com.aluraflix.service;

import com.aluraflix.entity.VideoEntity;
import com.aluraflix.exception.InternalServerErrorException;
import com.aluraflix.exception.NoContentException;
import com.aluraflix.exception.NotAcceptableException;
import com.aluraflix.exception.OkException;
import com.aluraflix.mapper.VideoMapper;
import com.aluraflix.model.VideoDto;
import com.aluraflix.respository.VideoRepository;
import com.aluraflix.validation.VideoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final VideoValidation videoValidation;

    public List<VideoDto> buscarTodosVideos() {
        List<VideoEntity> listaEntity = videoRepository.findAll();

        if (listaEntity.isEmpty()) {
            throw new NoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoDto(listaEntity);
    }

    public VideoDto buscarVideoPorId(Long id) {
        VideoEntity entity = Optional.ofNullable(videoRepository.findById(id)
                .orElseThrow(() -> new OkException("Video com Id " + id + " nao encontrado!"))).get();

        return videoMapper.converterVideoEntityParaVideoDto(entity);
    }

    public VideoDto cadastrarVideo(VideoDto videoDto) {
//      Certificar que nao contem Id, pois esse metodo é para cadastrar um novo video e nao atualizar
        if (videoDto.getId() != null) {
            throw new NotAcceptableException("Nao pode conter Id para realizar o cadastro de um novo video!");
        }
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaSalvar(videoDto);

        return salvarVideo(videoDto);
    }

    public VideoDto alterarVideo(Long idVideo, VideoDto videoDto) {
        videoDto.setId(idVideo);

        List<VideoDto> listaVideoDto = buscarTodosVideos();
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaAlterar(videoDto, listaVideoDto);

        return salvarVideo(videoDto);
    }

    public VideoDto alterarVideoParcialmente(Long idVideo, VideoDto videoDto) {
        videoDto.setId(idVideo);

        List<VideoDto> listaVideoDto = buscarTodosVideos();
//      Validar se os campos foram preenchidos corretamente
        videoValidation.validarCamposVideoParaAlterarParcialmente(videoDto, listaVideoDto);

        return salvarVideo(incluirDadosDaBaseEmUmVideoDtoIncompleto(videoDto, buscarVideoPorId(idVideo)));
    }

    public void deletarVideo(Long idVideo) {

        try {
            VideoDto videoDto = buscarVideoPorId(idVideo);

            VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

            videoRepository.delete(videoEntity);
        } catch (OkException e) {
            throw new NotAcceptableException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao deletar video!");
        }
    }

    private VideoDto salvarVideo(VideoDto videoDto) {
        VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

        try {
            videoEntity = videoRepository.save(videoEntity);
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao cadastrar video!");
        }

        return videoMapper.converterVideoEntityParaVideoDto(videoEntity);
    }

    private VideoDto incluirDadosDaBaseEmUmVideoDtoIncompleto(VideoDto videoDto, VideoDto videoDtoCadastrado) {
        if (videoDto.getTitulo() == null) videoDto.setTitulo(videoDtoCadastrado.getTitulo());
        if (videoDto.getDescricao() == null) videoDto.setDescricao(videoDtoCadastrado.getDescricao());
        if (videoDto.getUrl() == null) videoDto.setUrl(videoDtoCadastrado.getUrl());

        return videoDto;
    }
}
