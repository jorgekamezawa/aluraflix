package com.aluraflix.service;

import com.aluraflix.entity.CategoriaEntity;
import com.aluraflix.entity.VideoEntity;
import com.aluraflix.exception.InternalServerErrorException;
import com.aluraflix.exception.NoContentException;
import com.aluraflix.exception.NotAcceptableException;
import com.aluraflix.exception.OkException;
import com.aluraflix.mapper.CategoriaMapper;
import com.aluraflix.model.CategoriaDto;
import com.aluraflix.model.VideoDto;
import com.aluraflix.respository.CategoriaRepository;
import com.aluraflix.validation.CategoriaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final CategoriaValidation categoriaValidation;

    public List<CategoriaDto> buscarTodasCategorias() {
        List<CategoriaEntity> listaCategoriaEntity = categoriaRepository.findAll();

        if (listaCategoriaEntity.isEmpty()) {
            throw new NoContentException();
        }

        return categoriaMapper.converterListaCategoriaEntityParaListaCategoriaDto(listaCategoriaEntity);
    }

    public CategoriaDto buscarCategoriaPorId(Long id) {
        CategoriaEntity categoriaEntity = Optional.ofNullable(categoriaRepository.findById(id)
                .orElseThrow(() -> new OkException("Video com Id " + id + " nao encontrado!"))).get();

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    public CategoriaDto cadastrarCategoria(CategoriaDto CategoriaDto) {
//      Certificar que nao contem Id, pois esse metodo é para cadastrar uma nova categoria e nao atualizar
        if (CategoriaDto.getId() != null) {
            throw new NotAcceptableException("Nao pode conter Id para realizar o cadastro de uma nova categoria!");
        }
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaSalvar(CategoriaDto);

        return salvarCategoria(CategoriaDto);
    }

    public CategoriaDto alterarCategoria(Long idCategoria, CategoriaDto categoriaDto) {
        categoriaDto.setId(idCategoria);

        List<CategoriaDto> listaCategoriaDto = buscarTodasCategorias();
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaAlterar(categoriaDto, listaCategoriaDto);

        return salvarCategoria(categoriaDto);
    }

    public CategoriaDto alterarCategoriaParcialmente(Long idCategoria, CategoriaDto categoriaDto) {
        categoriaDto.setId(idCategoria);

        List<CategoriaDto> listaCategoriaDto = buscarTodasCategorias();
//      Validar se os campos foram preenchidos corretamente
        categoriaValidation.validarCamposCategoriaParaAlterarParcialmente(categoriaDto, listaCategoriaDto);

        return salvarCategoria(incluirDadosDaBaseEmUmaCategoriaDtoIncompleta(categoriaDto, buscarCategoriaPorId(idCategoria)));
    }

    public void deletarCategoria(Long idCategoria) {
        try {
            CategoriaDto categoriaDto = buscarCategoriaPorId(idCategoria);

            CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

            categoriaRepository.delete(categoriaEntity);
        } catch (OkException e) {
            throw new NotAcceptableException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao deletar categoria!");
        }
    }

    private CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = categoriaMapper.converterCategoriaDtoParaCategoriaEntity(categoriaDto);

        try {
            categoriaEntity = categoriaRepository.save(categoriaEntity);
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Erro ao cadastrar categoria!");
        }

        return categoriaMapper.converterCategoriaEntityParaCategoriaDto(categoriaEntity);
    }

    private CategoriaDto incluirDadosDaBaseEmUmaCategoriaDtoIncompleta(CategoriaDto categoriaDto, CategoriaDto categoriaDtoCadastrado) {
        if (categoriaDto.getTitulo() == null) categoriaDto.setTitulo(categoriaDtoCadastrado.getTitulo());
        if (categoriaDto.getCor() == null) categoriaDto.setCor(categoriaDtoCadastrado.getCor());

        return categoriaDto;
    }
}
