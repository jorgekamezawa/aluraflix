package com.aluraflix.controller;

import com.aluraflix.model.VideoDto;
import com.aluraflix.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDto>> buscarTodosVideos(){
        List<VideoDto> listaVideoDto = videoService.buscarTodosVideos();

        return ResponseEntity.ok(listaVideoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> buscarVideoPorId(@PathVariable(name = "id") Long id){
        VideoDto videoDto = videoService.buscarVideoPorId(id);

        return ResponseEntity.ok(videoDto);
    }
}
