package br.com.herois.controller;

import br.com.herois.service.HeroiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HeroiController {

    /*@Autowired
    HeroiService heroiService;

    @GetMapping
    private ResponseEntity<List<HeroiDto>> findAll(){
        List<Heroi> herois = heroiService.findAll();
        return ResponseEntity.ok(HeroiDto.converterList(herois));
    }*/

}
