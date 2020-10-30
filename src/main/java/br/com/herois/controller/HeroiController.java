package br.com.herois.controller;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.service.HeroiService;
import br.com.herois.validation.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class HeroiController {

    @Autowired
    HeroiService heroiService;

    @GetMapping
    private ResponseEntity<List<Heroi>> findAll(){
        List<Heroi> herois = heroiService.findAll();
        return ResponseEntity.ok(herois);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id, @RequestHeader String authorization) {
        Optional<Heroi> heroi = heroiService.findById(id);
        if(!heroi.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroi);
    }

    @PostMapping
    @Transactional
    private ResponseEntity<?> save(@RequestBody @Valid HeroiForm form, UriComponentsBuilder uriBuilder){
        try{
            Heroi heroi = heroiService.insert(form);
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(heroi.getId()).toUri();
            return ResponseEntity.created(uri).body(heroi);
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("AuthenticationException", e.getMessage()));
        }
    }

}
