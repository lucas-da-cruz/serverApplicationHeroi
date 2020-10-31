package br.com.herois.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class HeroiServiceTest {

    @Autowired
    HeroiService heroiService;

    @Test
    public void deveRetornarHeroi(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formattedDate = LocalDateTime.now().format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        /*List<Heroi> listHeroi = heroiService.findAll();
        assertEquals(0, listHeroi.size());*/
    }
}
