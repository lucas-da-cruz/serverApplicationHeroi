package br.com.herois.config.security;

import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.repository.UsuarioAdminRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsuarioAdminRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioAdminRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);

        boolean valido = tokenService.isTokenValido(token);

        if (valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        //Pegou id do token
        Long idUsuario = tokenService.getIdUsuario(token);
        //Recuperou o objeto de usuario
        UsuarioAdmin usuarioAdmin = repository.findById(idUsuario).get();
        //
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(usuarioAdmin, null, usuarioAdmin.getAuthorities());

        //Força a autenticação
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        } else {
            return token.substring(7, token.length());
        }
    }
}
