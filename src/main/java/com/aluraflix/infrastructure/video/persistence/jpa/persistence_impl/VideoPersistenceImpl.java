package com.aluraflix.infrastructure.video.persistence.jpa.persistence_impl;

import com.aluraflix.domain.categoria.exception.CategoriaPersistenceException;
import com.aluraflix.domain.categoria.exception.CategoriaValueNotFoundException;
import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.common.model.PageDto;
import com.aluraflix.domain.video.adapter.VideoPersistenceAdapter;
import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import com.aluraflix.domain.video.exception.VideoNoContentException;
import com.aluraflix.domain.video.exception.VideoPersistenceException;
import com.aluraflix.domain.video.exception.VideoValueNotFoundException;
import com.aluraflix.domain.video.model.Video;
import com.aluraflix.infrastructure.categoria.persistence.jpa.entity.CategoriaPersistenceEntity;
import com.aluraflix.infrastructure.video.persistence.jpa.entity.VideoPersistenceEntity;
import com.aluraflix.infrastructure.categoria.persistence.jpa.mapper.CategoriaMapper;
import com.aluraflix.infrastructure.video.persistence.jpa.mapper.VideoMapper;
import com.aluraflix.infrastructure.video.persistence.jpa.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PageDto<Video> buscarTodaListaPaginadaDeVideos(Pageable paginavel) {
        Page<VideoPersistenceEntity> paginaVideoEntity = videoRepository.findAll(paginavel);

        if (paginaVideoEntity.getContent().isEmpty()) {
            throw new VideoNoContentException();
        }

        List<Video> listaVideos = videoMapper.converterListaVideoEntityParaListaVideoModel(paginaVideoEntity.getContent());

        return new PageDto<>(listaVideos, paginaVideoEntity.getTotalElements(), paginaVideoEntity.getTotalPages());
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
    public PageDto<Video> buscarListaPaginadaDeVideosPorCategoria(Categoria categoriaModel, Pageable paginavel) {
        CategoriaPersistenceEntity categoriaEntity =
                categoriaMapper.converterCategoriaModelParaCategoriaEntity(categoriaModel);

        Page<VideoPersistenceEntity> paginaVideoEntity = videoRepository.findAllByCategoria(categoriaEntity, paginavel);

        if (paginaVideoEntity.getContent().isEmpty()) {
            throw new VideoNoContentException();
        }

        List<Video> listaVideos = videoMapper.converterListaVideoEntityParaListaVideoModel(paginaVideoEntity.getContent());

        return new PageDto<>(listaVideos, paginaVideoEntity.getTotalElements(), paginaVideoEntity.getTotalPages());
    }


    @Override
    public PageDto<Video> buscarListaPaginadaVideoPorTitulo(String tituloVideo, Pageable paginavel) {
        Page<VideoPersistenceEntity> paginaVideoEntity = videoRepository.findAllByTituloContaining(tituloVideo, paginavel);

        if (paginaVideoEntity.getContent().isEmpty()) {
            throw new VideoNoContentException();
        }

        List<Video> listaVideos = videoMapper.converterListaVideoEntityParaListaVideoModel(paginaVideoEntity.getContent());

        return new PageDto<>(listaVideos, paginaVideoEntity.getTotalElements(), paginaVideoEntity.getTotalPages());
    }
}
