package br.com.herois.service;

import br.com.herois.model.dto.HeroiTabelaDto;
import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Poder;
import br.com.herois.model.entities.Universo;
import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.model.form.HeroiUpdateForm;
import br.com.herois.repository.HeroiRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroiServiceTest {

    @InjectMocks
    HeroiService heroiService;
    @Mock
    HeroiRepository heroiRepository;
    @Mock
    UsuarioAdminService usuarioAdminService;

    /**
     * Testa o método findByStatusAndUsuarioAdminId, realizando o mockagem com o banco de dados
     * e validando a lista de herói devolvida.
     *
     * Resultado esperando: uma lista do tipo HeroiTabelaDto, de tamanho igual a 2.
     */
    @Test
    public void testFindByStatusAndUsuarioAdminId(){
        //Mockagem
        List<Heroi> listHeroiMock = new ArrayList<>();
        Heroi heroi1 = new Heroi("Flash", new Universo(1l, "Trainee Comics"), Arrays.asList(new Poder(1l, "Velocidade")), true);
        Heroi heroi2 = new Heroi("Super Choque", new Universo(1l, "EY Comics"), Arrays.asList(new Poder(1l, "Choque")), true);
        listHeroiMock.add(heroi1);
        listHeroiMock.add(heroi2);
        when(heroiRepository.findByStatusAndUsuarioAdminId(true, 1l)).thenReturn(Arrays.asList(heroi1, heroi2));

        //Ação
        List<HeroiTabelaDto> listHeroi = heroiService.findByStatusAndUsuarioAdminId(true, 1l);

        //Assertividade
        assertEquals(2, listHeroi.size());
        verify(heroiRepository, times(1)).findByStatusAndUsuarioAdminId(true, 1l);

        assertEquals("Flash", listHeroi.get(0).getNome());
        assertEquals("Super Choque", listHeroi.get(1).getNome());

        assertEquals("Trainee Comics", listHeroi.get(0).getUniverso().getNome());
        assertEquals("EY Comics", listHeroi.get(1).getUniverso().getNome());
    }

    /**
     * Testa o método insert da classe heroiService, realizando a mockagem do objeto do tipo HeroiForm,
     * e validando se será enviado para o método save do JPA as informações corretas de Heroi.
     *
     * Resultado esperado: um objeto tipo Heroi, com os atributos esperados conforme passado como parametro.
     */
    @Test
    public void testeInsertNewHeroi(){
        //Mockagem
        HeroiForm heroiForm = new HeroiForm();
        heroiForm.setNome("Flash");
        heroiForm.setStatus(true);
        heroiForm.setUniverso(new Universo(1l, "EY Comics"));
        heroiForm.setPoder(Arrays.asList(new Poder(1l, "Velocidade")));
        UsuarioAdmin usuarioAdmin = new UsuarioAdmin("UserTest", "usertest@usertest");
        usuarioAdmin.setId(1l);
        heroiForm.setUsuarioAdmin(usuarioAdmin);

        Heroi heroi = heroiForm.converterHeroi();

        when(usuarioAdminService.findById(1l)).thenReturn(Optional.of(usuarioAdmin));
        when(heroiRepository.save((Heroi) any())).thenReturn(heroi);

        //Ação
        Heroi heroiSaved = heroiService.insert(heroiForm, 1l);

        //Assertividade
        verify(usuarioAdminService, times(1)).findById(1l);
        verify(heroiRepository, times(1)).save((Heroi) any());

        assertEquals(heroiForm.getNome(), heroiSaved.getNome());
        assertEquals(heroiForm.getStatus(), heroiSaved.getStatus());
        assertEquals(heroiForm.getUniverso().getNome(), heroiSaved.getUniverso().getNome());
        assertEquals(heroiForm.getPoder().get(0).getNome(), heroiSaved.getPoder().get(0).getNome());
        assertEquals(heroiForm.getUsuarioAdmin().getId(), heroiSaved.getUsuarioAdmin().getId());
        assertEquals(heroiForm.getUsuarioAdmin().getNome(), heroiSaved.getUsuarioAdmin().getNome());
        assertEquals(heroiForm.getUsuarioAdmin().getEmail(), heroiSaved.getUsuarioAdmin().getEmail());
    }

    /**
     * Testa o método update da classe heroiService, realizando a mockagem do objeto do tipo HeroiUpdateForm,
     * e validando se será enviado para o método save do JPA as informações corretas de Heroi.
     *
     * Resultado esperado: um objeto tipo Heroi, com os atributos esperados conforme passado como parametro.
     */
    @Test
    public void testeUpdateNewHeroi(){
        //Mockagem
        HeroiUpdateForm heroiUpdateForm = new HeroiUpdateForm();
        heroiUpdateForm.setNome("Flash");
        heroiUpdateForm.setStatus(true);
        heroiUpdateForm.setUniverso(new Universo(1l, "Trainee Comics"));
        heroiUpdateForm.setPoder(Arrays.asList(new Poder(1l, "Velocidade")));

        Heroi heroi = new Heroi("Super Choque", new Universo(1l, "EY Comics"), Arrays.asList(new Poder(1l, "Choque")), true);

        when(heroiRepository.save((Heroi) any())).thenReturn(heroi);

        //Ação
        Heroi heroiSaved = heroiService.update(heroiUpdateForm, heroi);

        //Assertividade
        verify(heroiRepository, times(1)).save((Heroi) any());

        assertEquals(heroiUpdateForm.getNome(), heroiSaved.getNome());
        assertEquals(heroiUpdateForm.getStatus(), heroiSaved.getStatus());
        assertEquals(heroiUpdateForm.getUniverso().getNome(), heroiSaved.getUniverso().getNome());
        assertEquals(heroiUpdateForm.getPoder().get(0).getNome(), heroiSaved.getPoder().get(0).getNome());
    }

}
