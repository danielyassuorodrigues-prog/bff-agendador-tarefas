package com.danieljava.bffagendadortarefas.business;



import com.danieljava.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.danieljava.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.danieljava.bffagendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;


    public TarefasDTOResponse gravarTarefas(String token , TarefasDTORequest dto){
        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token){
        return client.buscaTarefaPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token){
        client.deletaTarefaPorId(id, token);

    }

    public TarefasDTOResponse alteraStatus (Status status, String id, String token){
        return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token){
        return client.updateTarefas(dto, id, token);
    }








}
