package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.exception.categoria.CategoriaValueNotFoundException;
import com.aluraflix.domain.exception.categoria.CategoriaPersistenceException;
import com.aluraflix.domain.exception.video.VideoFieldNotAcceptableException;
import com.aluraflix.domain.exception.video.VideoNoContentException;
import com.aluraflix.domain.exception.video.VideoValueNotFoundException;
import com.aluraflix.domain.exception.video.VideoPersistenceException;
import com.aluraflix.domain.model.VideoDto;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.VideoMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoPersistenceAdapterImpl implements VideoPersistenceAdapter {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    @Override
    public List<VideoDto> buscarTodosVideos() {
        List<VideoEntity> listaEntity = videoRepository.findAll();

        if (listaEntity.isEmpty()) {
            throw new VideoNoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoDto(listaEntity);
    }

    @Override
    public VideoDto buscarVideoPorId(Long id) {
        VideoEntity entity = Optional.ofNullable(videoRepository.findById(id)
                .orElseThrow(() -> new VideoValueNotFoundException("Video com Id " + id + " nao encontrado!"))).get();

        return videoMapper.converterVideoEntityParaVideoDto(entity);
    }

    @Override
    @Transactional
    public VideoDto salvarVideo(VideoDto videoDto) {
        VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

        try {
            videoEntity = videoRepository.save(videoEntity);

        } catch (RuntimeException e) {
            throw new VideoPersistenceException("Erro ao cadastrar video!");
        }

        return videoMapper.converterVideoEntityParaVideoDto(videoEntity);
    }

    @Override
    @Transactional
    public VideoDto alterarVideo(VideoDto videoDto) {
        return salvarVideo(videoDto);
    }

    @Override
    @Transactional
    public void deletarVideoPorId(Long idVideo) {
        try {
            VideoDto videoDto = buscarVideoPorId(idVideo);
            VideoEntity videoEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

            videoRepository.delete(videoEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new VideoFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar video!");
        }
    }
}
