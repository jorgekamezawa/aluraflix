package com.aluraflix.application.categoria.rest.controller;

import com.aluraflix.domain.categoria.model.Categoria;
import com.aluraflix.domain.categoria.service.CategoriaService;
import com.aluraflix.domain.common.model.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<PageDto<Categoria>> buscarTodaListaPaginadaDeCategorias(Pageable paginavel) {
        PageDto<Categoria> paginaCategoria = categoriaService.buscarTodaListaPaginadaDeCategorias(paginavel);

        return ResponseEntity.ok(paginaCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable(name = "id") Long id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria body) {
        Categoria categoria = categoriaService.cadastrarCategoria(body);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> alterarCategoria(@PathVariable(name = "id") Long id, @RequestBody Categoria body) {
        Categoria categoria = categoriaService.alterarCategoriaCompletamente(id, body);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> alterarCategoriaParcialmente(@PathVariable(name = "id") Long id, @RequestBody Categoria body) {
        Categoria categoria = categoriaService.alterarCategoriaParcialmente(id, body);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable(name = "id") Long id) {
        categoriaService.deletarCategoria(id);

        return ResponseEntity.ok("Delecao da categoria com Id " + id + " efetuada com SUCESSO!");
    }
}
