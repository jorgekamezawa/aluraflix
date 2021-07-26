package com.aluraflix.validation;

import com.aluraflix.exception.NotAcceptableException;
import com.aluraflix.model.VideoDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Component
public class VideoValidation {

    public void validarSalvarVideo(VideoDto dto, List<VideoDto> listDto){
        if(!validarVideoId(dto, listDto)){
            throw new NotAcceptableException("Id nao encontrado! Favor enviar Id existente!");
        }

        if(dto.getTitulo() == null){
            throw new NotAcceptableException("Titulo nao pode ser nulo");
        } else if(dto.getTitulo().length() > 30){
            throw new NotAcceptableException("Titulo com numero de caracteres maior do que 30");
        }

        if(dto.getDescricao() == null){
            throw new NotAcceptableException("Descricao nao pode ser nulo");
        } else if(dto.getDescricao().length() > 50){
            throw new NotAcceptableException("Descricao com numero de caracteres maior do que 50");
        }

        validarUrl(dto.getUrl());
    }

    private boolean validarVideoId(VideoDto dto, List<VideoDto> listDto) {
        if(dto.getId() != null){
            return listDto.stream().anyMatch(entity -> entity.getId().equals(dto.getId()));
        }
        return true;
    }

    private void validarUrl(String urlString) {
        if(urlString == null){
            throw new NotAcceptableException("URL nao pode ser nula");
        } else if(urlString.length() > 50){
            throw new NotAcceptableException("URL com numero de caracteres maior do que 50");
        }

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
            throw new NotAcceptableException("URL fora do padrao!");
        }catch (IOException e) {
            throw new NotAcceptableException("URL inexistente!");
        }
    }
}
