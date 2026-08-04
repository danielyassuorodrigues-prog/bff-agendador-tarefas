package com.danieljava.bffagendadortarefas.business;



import com.danieljava.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.danieljava.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.danieljava.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.danieljava.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.danieljava.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.danieljava.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


     //metodo que salva usuario
    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    //busca o usuario atraves do email, metodo pronto do JPA tambem
    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    //deleta o email
    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    //metodo para atualizar os dados do usuario, como não é obrigatório ele nos passar o email , então temos que extrair do token
    public UsuarioDTOResponse atualizaDadosUsuario(UsuarioDTORequest dto, String token) {
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco (Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);

    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
        return usuarioClient.cadastraTelefone(dto, token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep){
        return usuarioClient.buscarDadosCep(cep);
    }





}
