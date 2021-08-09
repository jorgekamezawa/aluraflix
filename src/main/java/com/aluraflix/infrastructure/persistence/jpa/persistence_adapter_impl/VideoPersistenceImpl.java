package com.aluraflix.infrastructure.persistence.jpa.persistence_adapter_impl;

import com.aluraflix.domain.video.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.exception.VideoNoContentException;
import com.aluraflix.domain.video.exception.VideoValueNotFoundException;
import com.aluraflix.domain.video.exception.VideoPersistenceException;
import com.aluraflix.domain.video.model.VideoDto;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.VideoMapper;
import com.aluraflix.infrastructure.persistence.jpa.respository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoPersistenceImpl implements VideoPersistenceAdapter {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    @Override
    public List<VideoDto> buscarTodosVideos() {
        List<VideoPersistenceEntity> listaEntity = videoRepository.findAll();

        if (listaEntity.isEmpty()) {
            throw new VideoNoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoDto(listaEntity);
    }

    @Override
    public VideoDto buscarVideoPorId(Long id) {
        VideoPersistenceEntity entity = Optional.ofNullable(videoRepository.findById(id)
                .orElseThrow(() -> new VideoValueNotFoundException("Video com Id " + id + " nao encontrado!"))).get();

        return videoMapper.converterVideoEntityParaVideoDto(entity);
    }

    @Override
    @Transactional
    public VideoDto salvarVideo(VideoDto videoDto) {
        VideoPersistenceEntity videoPersistenceEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

        try {
            videoPersistenceEntity = videoRepository.save(videoPersistenceEntity);

        } catch (RuntimeException e) {
            throw new VideoPersistenceException("Erro ao cadastrar video!");
        }

        return videoMapper.converterVideoEntityParaVideoDto(videoPersistenceEntity);
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
            VideoPersistenceEntity videoPersistenceEntity = videoMapper.converterVideoDtoParaVideoEntity(videoDto);

            videoRepository.delete(videoPersistenceEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new VideoFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar video!");
        }
    }
}
