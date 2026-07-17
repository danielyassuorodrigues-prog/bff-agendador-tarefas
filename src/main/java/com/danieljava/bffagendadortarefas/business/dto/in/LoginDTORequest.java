package com.danieljava.bffagendadortarefas.business.dto.in;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {
    private String name;
    private String senha;
}
