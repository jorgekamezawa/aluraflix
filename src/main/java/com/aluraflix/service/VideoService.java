package com.aluraflix.service;

import com.aluraflix.entity.VideoEntity;
import com.aluraflix.exception.model.NoContentException;
import com.aluraflix.mapper.VideoEntityParaVideoDtoMapper;
import com.aluraflix.model.VideosDto;
import com.aluraflix.respository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepostiory;
    private final VideoEntityParaVideoDtoMapper videoEntityParaVideoDtoMapper;

    public List<VideosDto> buscarTodosVideos() {
        List<VideoEntity> listaVideo = videoRepostiory.findAll();

        if(listaVideo.isEmpty()) {
            throw new NoContentException("Nao contem videos cadastrados");
        }

        return videoEntityParaVideoDtoMapper.converter(listaVideo);
    }
}
