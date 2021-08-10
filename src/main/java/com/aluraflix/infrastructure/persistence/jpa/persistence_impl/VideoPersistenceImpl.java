package com.aluraflix.infrastructure.persistence.jpa.persistence_impl;

import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.video.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.exception.VideoNoContentException;
import com.aluraflix.domain.video.exception.VideoPersistenceException;
import com.aluraflix.domain.video.exception.VideoValueNotFoundException;
import com.aluraflix.domain.video.model.Video;
import com.aluraflix.infrastructure.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.entity.VideoPersistenceEntity;
import com.aluraflix.infrastructure.persistence.jpa.mapper.CategoriaMapper;
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
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<Video> buscarTodosVideos() {
        List<VideoPersistenceEntity> listaVideoEntity = videoRepository.findAll();

        if (listaVideoEntity.isEmpty()) {
            throw new VideoNoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoModel(listaVideoEntity);
    }

    @Override
    public Video buscarVideoPorId(Long idVideo) {
        VideoPersistenceEntity videoEntity = Optional.ofNullable(videoRepository.findById(idVideo)
                .orElseThrow(() -> new VideoValueNotFoundException("Video com Id " + idVideo + " nao encontrado!"))).get();

        return videoMapper.converterVideoEntityParaVideoModel(videoEntity);
    }

    @Override
    @Transactional
    public Video salvarVideo(Video videoModel) {
        VideoPersistenceEntity videoEntity = videoMapper.converterVideoModelParaVideoEntity(videoModel);

        try {
            videoEntity = videoRepository.save(videoEntity);

        } catch (RuntimeException e) {
            throw new VideoPersistenceException("Erro ao cadastrar videoModel!");
        }

        return videoMapper.converterVideoEntityParaVideoModel(videoEntity);
    }

    @Override
    @Transactional
    public Video alterarVideo(Video videoModel) {
        return salvarVideo(videoModel);
    }

    @Override
    @Transactional
    public void deletarVideoPorId(Long idVideo) {
        try {
            Video videoModel = buscarVideoPorId(idVideo);
            VideoPersistenceEntity videoEntity = videoMapper.converterVideoModelParaVideoEntity(videoModel);

            videoRepository.delete(videoEntity);

        } catch (CategoriaValueNotFoundException e) {
            throw new VideoFieldNotAcceptableException(e.getMessage() + " Favor informar id existente!");

        } catch (RuntimeException e) {
            throw new CategoriaPersistenceException("Erro ao deletar video!");
        }
    }

    @Override
    public List<Video> buscarVideoPorCategoria(Categoria categoriaModel) {
        CategoriaPersistenceEntity categoriaEntity =
                categoriaMapper.converterCategoriaModelParaCategoriaEntity(categoriaModel);
        List<VideoPersistenceEntity> listaVideoEntity = videoRepository.findAllByCategoria(categoriaEntity);

        if (listaVideoEntity.isEmpty()) {
            throw new VideoNoContentException();
        }

        return videoMapper.converterListaVideoEntityParaListaVideoModel(listaVideoEntity);
    }
}
