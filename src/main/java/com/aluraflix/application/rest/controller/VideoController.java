package com.aluraflix.application.rest.controller;

import com.aluraflix.domain.video.model.Video;
import com.aluraflix.domain.video.service.VideoService;
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
    public ResponseEntity<List<Video>> buscarTodosVideos() {
        List<Video> listaVideo = videoService.buscarTodosVideos();

        return ResponseEntity.ok(listaVideo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> buscarVideoPorId(@PathVariable(name = "id") Long id) {
        Video video = videoService.buscarVideoPorId(id);

        return ResponseEntity.ok(video);
    }

    @PostMapping
    public ResponseEntity<Video> cadastrarVideo(@RequestBody Video body) {
        Video video = videoService.cadastrarVideo(body);

        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> alterarVideo(@PathVariable(name = "id") Long id, @RequestBody Video body) {
        Video video = videoService.alterarVideoCompletamente(id, body);

        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Video> alterarVideoParcialmente(@PathVariable(name = "id") Long id, @RequestBody Video body) {
        Video video = videoService.alterarVideoParcialmente(id, body);

        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVideo(@PathVariable(name = "id") Long id) {
        videoService.deletarVideo(id);

        return ResponseEntity.ok("Delecao do video com Id " + id + " efetuada com SUCESSO!");
    }

    @GetMapping("/categorias/{id_categoria}")
    public ResponseEntity<List<Video>> buscarVideoPorCategoria(@PathVariable(name = "id_categoria") Long idCateogria){

        List<Video> listaVideo = videoService.buscarVideoPorCategoria(idCateogria);

        return ResponseEntity.ok(listaVideo);
    }
}
