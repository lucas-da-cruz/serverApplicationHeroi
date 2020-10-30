package br.com.herois.controller;

import br.com.herois.model.entities.Universo;
import br.com.herois.service.UniversoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/universo")
public class UniversoController {

    @Autowired
    UniversoService universoService;

    @GetMapping
    private ResponseEntity<List<Universo>> findAll(){
        List<Universo> universos = universoService.findAll();
        return ResponseEntity.ok(universos);
    }
}
