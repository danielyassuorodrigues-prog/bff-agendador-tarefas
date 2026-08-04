package com.danieljava.bffagendadortarefas.business;



import com.danieljava.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.danieljava.bffagendadortarefas.infrastructure.client.EmailClient;
import com.danieljava.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.danieljava.bffagendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;


    public void enviaEmail(TarefasDTOResponse dto) {
        client.enviarEmail(dto);
    }









}
