package br.com.herois.service;

import br.com.herois.model.entities.Heroi;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeroiServiceTest {

    @Autowired
    HeroiService heroiService;

    @Test
    public void deveRetornarHeroi(){
        List<Heroi> listHeroi = heroiService.findAll();
        assertEquals(0, listHeroi.size());
    }
}
