package com.aluraflix.service;

import com.aluraflix.entity.VideoEntity;
import com.aluraflix.exception.NoContentException;
import com.aluraflix.exception.OkException;
import com.aluraflix.mapper.VideoMapper;
import com.aluraflix.model.VideoDto;
import com.aluraflix.respository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepostiory;
    private final VideoMapper videoMapper;

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
}
