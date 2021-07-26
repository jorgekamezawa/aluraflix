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

    private final VideoRepository videoRepostiory;
    private final VideoMapper videoMapper;
    private final VideoValidation videoValidation;

    public List<VideoDto> buscarTodosVideos() {
        List<VideoEntity> listaVideoEntity = videoRepostiory.findAll();

        if(listaVideoEntity.isEmpty()) {
            throw new NoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoDto(listaVideoEntity);
    }

    public VideoDto buscarVideoPorId(Long id) {
        VideoEntity videoEntity = Optional.ofNullable(videoRepostiory.findById(id)
                .orElseThrow(() -> new OkException("Video com Id " + id + " nao encontrado!"))).get();

        return videoMapper.converterVideoEntityParaVideoDto(videoEntity);
    }

    public VideoDto salvarVideo(VideoDto videoDto) {
//      Certificar que nao contem Id, pois esse metodo é para cadastrar um novo video e nao atualizar
        if(videoDto.getId() != null){
            throw new NotAcceptableException("Nao pode conter Id para realizar o cadastro de um novo video!");
        }
//      Validar se os campos foram preenchidos corretamente
        List<VideoDto> listaVideoDto = buscarTodosVideos();
        videoValidation.validarSalvarVideo(videoDto, listaVideoDto);

        VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

        try{
            videoEntity = videoRepostiory.save(videoEntity);
        }catch (RuntimeException e){
            throw new InternalServerErrorException(e.getMessage());
        }

        return videoMapper.converterVideoEntityParaVideoDto(videoEntity);
    }
}
