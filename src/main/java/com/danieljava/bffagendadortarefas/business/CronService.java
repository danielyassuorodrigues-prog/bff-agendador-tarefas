package com.danieljava.bffagendadortarefas.business;


import com.danieljava.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.danieljava.bffagendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService service;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura =  LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(5);
        List<TarefasDTOResponse> listaTarefas =  service.buscaTarefasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas " + listaTarefas);

        for (TarefasDTOResponse listaTarefa : listaTarefas) {
            emailService.enviaEmail(listaTarefa);
            log.info("Email enviado " + listaTarefa.getEmailUsuario());
            service.alteraStatus(Status.NOTIFICADO, listaTarefa.getId(), token);
        }
        log.info("Finaliza a busca e notificação de tarefas ");
;    }

    public String login(LoginDTORequest dto) {
       return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
