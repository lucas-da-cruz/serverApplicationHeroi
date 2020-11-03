package br.com.herois.controller;

import br.com.herois.service.TokenService;
import br.com.herois.model.dto.TokenDto;
import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.form.LoginForm;
import br.com.herois.service.UsuarioAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioAdminService usuarioAdminService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm user){
        UsernamePasswordAuthenticationToken dadosLogin = user.converter();
        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            List<String> authorities = authentication.getAuthorities().stream()
                    .map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.toList());

            return ResponseEntity.ok(new TokenDto(token, "Bearer", authorities));
            } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/isAuthenticated")
    public ResponseEntity isAuthenticated(){
        return ResponseEntity.ok().build();
    }
}
