package com.aluraflix.controller;

import com.aluraflix.model.VideosDto;
import com.aluraflix.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
@AllArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideosDto>> buscarTodosVideos(){
        List<VideosDto> listaVideoDto = videoService.buscarTodosVideos();

        return ResponseEntity.ok(listaVideoDto);
    }
}
