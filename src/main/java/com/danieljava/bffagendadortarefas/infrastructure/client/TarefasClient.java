package com.danieljava.bffagendadortarefas.infrastructure.client;


import com.danieljava.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.danieljava.bffagendadortarefas.infrastructure.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "usuario" , url = "${agendador-tarefas.url}")
public interface TarefasClient {
    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
   List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefaPorEmail (@RequestHeader("Authorization") String token) ;

    @DeleteMapping
   void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);
    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") Status status,
                                               @RequestParam("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas (@RequestBody TarefasDTORequest dto, @RequestParam String id, @RequestHeader("Authorization") String token);
}
