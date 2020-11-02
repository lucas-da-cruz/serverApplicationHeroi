package br.com.herois.service;

import br.com.herois.model.entities.UsuarioAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AutenticacaoServiceTest {

    @InjectMocks
    AutenticacaoService autenticacaoService;
    @Mock
    UsuarioAdminService usuarioAdminService;

    @Test
    public void testLoadUserByUsername(){
        String email = "userTest@test.com";
        UsuarioAdmin usuarioAdmin = new UsuarioAdmin("User Teste", email);
        usuarioAdmin.setId(1l);
        usuarioAdmin.setSenha("$2a$10$VRJBo4NBxxMFlNsOwQwLme6V2efb/YoLCu7eVRB31.wAyuyRFBeH6");

        when(usuarioAdminService.findByEmail(email)).thenReturn(Optional.of(usuarioAdmin));

        UserDetails userDetails = autenticacaoService.loadUserByUsername(email);

        assertEquals(userDetails.getUsername(), email);
        assertEquals(userDetails.getPassword(), usuarioAdmin.getSenha());
    }

}
