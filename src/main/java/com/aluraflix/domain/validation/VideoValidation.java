package com.aluraflix.domain.validation;

import com.aluraflix.domain.exception.video.VideoFieldNotAcceptableException;
import com.aluraflix.domain.model.VideoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoValidation {

    public void validarCamposVideoParaCadastrar(VideoDto videoDto) {
        validarQueIdSejaNulo(videoDto);
        validarQueCamposNaoSejamNulos(videoDto);
        validarTamanhoCampos(videoDto);
        validarUrl(videoDto.getUrl());
    }

    public void validarCamposVideoParaAlterar(VideoDto videoDto) {
        validarQueCamposNaoSejamNulos(videoDto);
        validarTamanhoCampos(videoDto);
        validarUrl(videoDto.getUrl());
    }

    public void validarCamposVideoParaAlterarParcialmente(VideoDto videoDto) {
        validarSeContemPeloMenosUmCampoPreenchido(videoDto);
        validarParaNaoTerTodosCampoPreenchidos(videoDto);
        validarTamanhoCampos(videoDto);
        if (videoDto.getUrl() != null) validarUrl(videoDto.getUrl());
    }

    private void validarQueIdSejaNulo(VideoDto videoDto) {
//      Certificar que nao contem Id, pois o metodo é para cadastrar um novo video e nao atualizar!
        if (videoDto.getId() != null) {
            throw new VideoFieldNotAcceptableException("Nao pode conter Id para realizar o cadastro de um novo video!");
        }
    }

    public void validarQueCamposNaoSejamNulos(VideoDto videoDto) {
        List<String> erros = new ArrayList<>();

        if (videoDto.getTitulo() == null) {
            erros.add("O campo titulo nao pode ser nulo");
        }
        if (videoDto.getDescricao() == null) {
            erros.add("O campo descricao nao pode ser nulo");
        }
        if (videoDto.getUrl() == null) {
            erros.add("O campo url nao pode ser nulo");
        }
        if (videoDto.getCategoria() == null) {
            erros.add("A categoria nao pode ser nula");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos nulos", erros);
        }
    }

    public void validarTamanhoCampos(VideoDto videoDto) {
        List<String> erros = new ArrayList<>();

        int tamanho30 = 30;
        int tamanho50 = 50;

        if (videoDto.getTitulo() != null && videoDto.getTitulo().length() > tamanho30) {
            erros.add("O campo titulo nao pode ter mais que " + tamanho30 + " caracteres");
        }
        if (videoDto.getDescricao() != null && videoDto.getDescricao().length() > tamanho50) {
            erros.add("O campo descricao nao pode ter mais que " + tamanho50 + " caracteres");
        }
        if (videoDto.getUrl() != null && videoDto.getUrl().length() > tamanho50) {
            erros.add("O campo url nao pode ter mais que " + tamanho50 + " caracteres");
        }

        if (!erros.isEmpty()) {
            throw new VideoFieldNotAcceptableException("Contem campos com numero de caracteres excedido", erros);
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
            throw new VideoFieldNotAcceptableException("URL fora do padrao, tem que etsar dentro do protocolo de transferência HTTP!");

        } catch (IOException e) {
//          Excecao gerada quando na URL realmente nao existe
            throw new VideoFieldNotAcceptableException("URL inexistente!");
        }
    }

    private void validarSeContemPeloMenosUmCampoPreenchido(VideoDto videoDto) {
        if (videoDto.getTitulo() == null && videoDto.getDescricao() == null &&
                videoDto.getUrl() == null) {
            throw new VideoFieldNotAcceptableException("Nenhum campo enviado para alteracao!");
        }
    }

    private void validarParaNaoTerTodosCampoPreenchidos(VideoDto videoDto) {
        if (videoDto.getTitulo() != null && videoDto.getDescricao() != null &&
                videoDto.getUrl() != null) {
            throw new VideoFieldNotAcceptableException("Essa API é para alterar somente parte dos campos porem " +
                    "todos os campos foram enviados!");
        }
    }
}
