package com.aluraflix.application.rest.controller;

import com.aluraflix.domain.common.model.PageDto;
import com.aluraflix.domain.video.model.Video;
import com.aluraflix.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<PageDto<Video>> buscarListaPaginadaDeVideosPorFiltro(
            @RequestParam(name = "titulo", required = false) String titulo,
            Pageable paginavel) {
        PageDto<Video> paginaVideo = videoService.buscarListaPaginadaDeVideosPorFiltro(titulo, paginavel);

        return ResponseEntity.ok(paginaVideo);
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
    public ResponseEntity<PageDto<Video>> buscarListaPaginadaDeVideosPorCategoria(
            @PathVariable(name = "id_categoria") Long idCateogria,
            Pageable paginavel) {

        PageDto<Video> paginaVideo = videoService.buscarListaPaginadaDeVideosPorCategoria(idCateogria, paginavel);

        return ResponseEntity.ok(paginaVideo);
    }
}
