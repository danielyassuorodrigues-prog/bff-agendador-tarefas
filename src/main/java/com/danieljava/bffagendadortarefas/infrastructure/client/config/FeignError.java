package com.danieljava.bffagendadortarefas.infrastructure.client.config;

import com.danieljava.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String messagemErro = mensagemErro(response);

        switch (response.status()){
            case 409 :
                return new ConflictException("Erro: " +messagemErro);
            case 403 :
                return new ResourceNotFoundException("Erro: " +messagemErro);
            case 401 :
                return new UnauthorizedException("Erro: " +messagemErro);
            case 400 :
                return new IllegalArgumentException("Erro: " +messagemErro);
            default:
                return new BusinessException("Erro: " +messagemErro);

        }
    }

    private String mensagemErro(Response response){
        try{
            if(Objects.isNull(response.body())){
                return "";
            }
           return  new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
