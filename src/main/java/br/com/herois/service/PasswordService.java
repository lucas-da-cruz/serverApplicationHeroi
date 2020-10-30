package br.com.herois.service;

import br.com.herois.config.security.TokenService;
import br.com.herois.model.entities.UsuarioAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class PasswordService {

    /*@Autowired
    UsuarioAdminService usuarioAdminService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;
    @Value("${fronted.domain}")
    private String domainFrontend;*/

    /*public String generateNewPass(String email){
        String subject = "Geração de nova senha";
        Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findByEmail(email);

        //Removendo barra do parametro
        usuarioAdmin.get().setSenha(usuarioAdmin.get().getSenha().replace("/", ""));

        String message = "Olá " + usuarioAdmin.get().getNome() + ", detectamos que foi " +
                "solicitado uma nova geração de senha para sua conta (" + usuarioAdmin.get().getEmail() + ") " +
                "na plataforma 3Cs (Conexão Cidade e Cidadão), se essa foi uma solicitação feita por você, basta clicar no link " +
                "abaixo e inserir sua nova senha, caso contrário ignore esse email.\n";

        message = message + "\n Clique no link abaixo para ser redirecionado para a redefinição de senha: "
                + domainFrontend + "/"
                + usuarioAdmin.get().getId() + "/"
                + usuarioAdmin.get().getEmail() + "/"
                + usuarioAdmin.get().getSenha();

        return javaMailApp.sendEmail(email, subject, message);
    }*/

}
