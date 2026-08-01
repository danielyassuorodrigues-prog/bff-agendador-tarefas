package com.danieljava.bffagendadortarefas.infrastructure.client.config;

import com.danieljava.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.danieljava.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409 :
                return new ConflictException("Erro, atributo ja existente");
            case 403 :
                return new ResourceNotFoundException("Erro atributo não encontrado");
            case 401 :
                return new UnauthorizedException("Erro usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");

        }
    }
}
