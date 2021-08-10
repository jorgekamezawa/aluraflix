package com.aluraflix.domain.video.validation;

import com.aluraflix.domain.video.exception.VideoFieldNotAcceptableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class VideoValidarUrl {

    public void validar(String urlString) {
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
}
