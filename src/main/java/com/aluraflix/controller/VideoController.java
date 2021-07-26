package com.aluraflix.controller;

import com.aluraflix.model.VideoDto;
import com.aluraflix.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<VideoDto> cadastrarVideo(@RequestBody VideoDto body){
        VideoDto videoDto = videoService.cadastrarVideo(body);

        return new ResponseEntity<>(videoDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> alterarVideo(@PathVariable(name = "id") Long id, @RequestBody VideoDto body){
        VideoDto videoDto = videoService.alterarVideo(id, body);

        return new ResponseEntity<>(videoDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VideoDto> alterarVideoParcialmente(@PathVariable(name = "id") Long id, @RequestBody VideoDto body){
        VideoDto videoDto = videoService.alterarVideoParcialmente(id, body);

        return new ResponseEntity<>(videoDto, HttpStatus.CREATED);
    }
}
