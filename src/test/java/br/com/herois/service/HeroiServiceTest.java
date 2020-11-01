package br.com.herois.service;

import br.com.herois.model.dto.HeroiTabelaDto;
import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Poder;
import br.com.herois.model.entities.Universo;
import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.repository.HeroiRepository;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Heroi> listHeroiMock = new ArrayList<>();
        Heroi heroi1 = new Heroi("Flash", new Universo(1l, "Trainee Comics"), Arrays.asList(new Poder(1l, "Velocidade")), true);
        Heroi heroi2 = new Heroi("Super Choque", new Universo(1l, "EY Comics"), Arrays.asList(new Poder(1l, "Choque")), true);
        listHeroiMock.add(heroi1);
        listHeroiMock.add(heroi2);
        when(heroiRepository.findByStatusAndUsuarioAdminId(true, 1l)).thenReturn(Arrays.asList(heroi1, heroi2));

        List<HeroiTabelaDto> listHeroi = heroiService.findByStatusAndUsuarioAdminId(true, 1l);

        assertEquals(2, listHeroi.size());
        verify(heroiRepository, times(1)).findByStatusAndUsuarioAdminId(true, 1l);

        assertEquals("Flash", listHeroi.get(0).getNome());
        assertEquals("Super Choque", listHeroi.get(1).getNome());

        assertEquals("Trainee Comics", listHeroi.get(0).getUniverso().getNome());
        assertEquals("EY Comics", listHeroi.get(1).getUniverso().getNome());
    }
}
