package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.model.VideoDto;
import com.aluraflix.domain.exception.InternalServerErrorException;
import com.aluraflix.domain.exception.NoContentException;
import com.aluraflix.domain.exception.NotAcceptableException;
import com.aluraflix.domain.exception.OkException;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.VideoMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoPersistenceAdapterImpl implements VideoPersistenceAdapter {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

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

    public void deletarVideoPorId(Long idVideo) {

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

    public VideoDto salvarVideo(VideoDto videoDto) {
        VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

        try {
            videoEntity = videoRepository.save(videoEntity);
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao cadastrar video!");
        }

        return videoMapper.converterVideoEntityParaVideoDto(videoEntity);
    }
}
