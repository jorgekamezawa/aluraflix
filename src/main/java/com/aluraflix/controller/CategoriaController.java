package com.aluraflix.controller;

import com.aluraflix.model.CategoriaDto;
import com.aluraflix.model.VideoDto;
import com.aluraflix.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodasCategorias() {
        List<CategoriaDto> listaCategoriaDto = categoriaService.buscarTodasCategorias();

        return ResponseEntity.ok(listaCategoriaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable(name = "id") Long id) {
        CategoriaDto categoriaDto = categoriaService.buscarCategoriaPorId(id);

        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody CategoriaDto body) {
        CategoriaDto categoriaDto = categoriaService.cadastrarCategoria(body);

        return new ResponseEntity<>(categoriaDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> alterarCategoria(@PathVariable(name = "id") Long id, @RequestBody CategoriaDto body) {
        CategoriaDto categoriaDto = categoriaService.alterarCategoria(id, body);

        return new ResponseEntity<>(categoriaDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaDto> alterarCategoriaParcialmente(@PathVariable(name = "id") Long id, @RequestBody CategoriaDto body) {
        CategoriaDto categoriaDto = categoriaService.alterarCategoriaParcialmente(id, body);

        return new ResponseEntity<>(categoriaDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable(name = "id") Long id) {
        categoriaService.deletarCategoria(id);

        return ResponseEntity.ok("Delecao da categoria com Id " + id + " efetuada com SUCESSO!");
    }
}
