package br.com.herois.controller;

import br.com.herois.model.entities.Poder;
import br.com.herois.service.PoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poder")
public class PoderController {

    @Autowired
    PoderService poderService;

    @GetMapping
    private ResponseEntity<List<Poder>> findAll(){
        List<Poder> listPoderes = poderService.findAll();
        return ResponseEntity.ok(listPoderes);
    }

}
