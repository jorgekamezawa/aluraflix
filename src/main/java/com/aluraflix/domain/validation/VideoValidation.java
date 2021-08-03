package com.aluraflix.domain.validation;

import com.aluraflix.domain.exception.NotAcceptableException;
import com.aluraflix.domain.model.VideoDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidation {

    public void validarCamposVideoParaSalvar(VideoDto dto) {
        validarCamposNulos(dto);
        validarTamanhoCampos(dto);
        validarUrl(dto.getUrl());
    }

    public void validarCamposVideoParaAlterar(VideoDto dto, List<VideoDto> listDto) {
        validarId(dto, listDto);
        validarCamposVideoParaSalvar(dto);
    }

    public void validarCamposVideoParaAlterarParcialmente(VideoDto dto, List<VideoDto> listDto) {
        validarId(dto, listDto);
        validarSeContemPeloMenosUmCampoPreenchido(dto);
        validarParaNaoTerTodosCampoPreenchidos(dto);
        validarTamanhoCampos(dto);

        if (dto.getUrl() != null) validarUrl(dto.getUrl());
    }

    public void validarCamposNulos(VideoDto dto) {
        List<String> erros = new ArrayList<>();

        if (dto.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (dto.getDescricao() == null) {
            erros.add("O campo descricao nao pode ser nulo");
        }
        if (dto.getUrl() == null) {
            erros.add("O campo url nao pode ser nulo");
        }
        if (!erros.isEmpty()) {
            throw new NotAcceptableException("Contem campos nulos", erros);
        }
    }

    private void validarSeContemPeloMenosUmCampoPreenchido(VideoDto dto) {
        if (dto.getTitulo() == null && dto.getDescricao() == null && dto.getUrl() == null) {
            throw new NotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }

    private void validarParaNaoTerTodosCampoPreenchidos(VideoDto dto) {
        if (dto.getTitulo() != null && dto.getDescricao() != null && dto.getUrl() != null) {
            throw new NotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
        }
    }

    public void validarTamanhoCampos(VideoDto dto) {
        List<String> erros = new ArrayList<>();

        int tamanho30 = 30;
        int tamanho50 = 50;

        if (dto.getTitulo() != null && dto.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (dto.getDescricao() != null && dto.getDescricao().length() > tamanho50) {
            erros.add("O campo descricao nao pode ter mais que " + tamanho50 + " caracteres");
        }
        if (dto.getUrl() != null && dto.getUrl().length() > tamanho50) {
            erros.add("O campo url nao pode ter mais que " + tamanho50 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new NotAcceptableException("Contem campos com numero de caracteres excedido", erros);
        }
    }

    private void validarId(VideoDto videoDto, List<VideoDto> listVideoDto) {
        if (videoDto.getId() == null) {
            return;
        }
        if (listVideoDto.stream().noneMatch(videoEntity -> videoEntity.getId().equals(videoDto.getId()))) {
            throw new NotAcceptableException("Id nao encontrado! Favor enviar Id existente!");
        }
    }

    private void validarUrl(String urlString) {
        try {
//          Se a URL der um NOT_FOUND vai passar por essa validacao pois a estrutura da URL esta correta,
//          mesmo a rota podendo nao estar inteiramente correta
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
//          Excecao gerada quando na URL nao tem o http:// ou https://
            throw new NotAcceptableException("URL fora do padrao, tem que etsar dentro do protocolo de transferência HTTP!");
        } catch (IOException e) {
//          Excecao gerada quando na URL realmente nao existe
            throw new NotAcceptableException("URL inexistente!");
        }
    }
}
