package br.com.herois.controller;

import br.com.herois.config.security.TokenService;
import br.com.herois.model.dto.HeroiDto;
import br.com.herois.model.dto.HeroiTabelaDto;
import br.com.herois.model.entities.Heroi;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.model.form.HeroiUpdateForm;
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
@RequestMapping("/heroi")
public class HeroiController {

    @Autowired
    HeroiService heroiService;
    @Autowired
    TokenService tokenService;

    @GetMapping
    private ResponseEntity<?> findAll(@RequestHeader String authorization){
        Long id = tokenService.getId(authorization);
        List<HeroiTabelaDto> herois = heroiService.findByStatusAndUsuarioAdminId(id);
        return ResponseEntity.ok(herois);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id, @RequestHeader String authorization) {
        Optional<Heroi> heroi = heroiService.findById(id);
        if(!heroi.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(heroi.get().getUsuarioAdmin().getId() != tokenService.getId(authorization)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        return ResponseEntity.ok(new HeroiDto(heroi.get()));
    }

    @Transactional
    @PostMapping
    private ResponseEntity<?> save(@RequestBody @Valid HeroiForm form, UriComponentsBuilder uriBuilder, @RequestHeader String authorization){
        try{
            Long id = tokenService.getId(authorization);
            Heroi heroi = heroiService.insert(form, id);
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(heroi.getId()).toUri();
            return ResponseEntity.created(uri).body(new HeroiDto(heroi));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("AuthenticationException", e.getMessage()));
        }
    }

    @Transactional
    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid HeroiUpdateForm newHeroi, @RequestHeader String authorization){
        Optional<Heroi> heroi = heroiService.findById(id);
        if(!heroi.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(heroi.get().getUsuarioAdmin().getId() != tokenService.getId(authorization)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        Heroi heroiUpdated = heroiService.update(newHeroi, heroi.get());
        return ResponseEntity.ok(new HeroiDto(heroiUpdated));
    }

    @Transactional
    @DeleteMapping("/{id}")
    private ResponseEntity<?> desativeById(@PathVariable Long id, @RequestHeader String authorization){
        Optional<Heroi> heroi = heroiService.findById(id);
        if(!heroi.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(heroi.get().getUsuarioAdmin().getId() != tokenService.getId(authorization)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        heroiService.desative(heroi.get());
        return ResponseEntity.ok().build();
    }

}
