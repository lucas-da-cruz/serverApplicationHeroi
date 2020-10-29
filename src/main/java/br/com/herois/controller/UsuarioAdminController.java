package br.com.herois.controller;

import br.com.herois.config.security.TokenService;
import br.com.herois.model.dto.UsuarioAdminDto;
import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.exception.EmailExistenteException;
import br.com.herois.model.form.UsuarioAdminForm;
import br.com.herois.model.form.UsuarioAdminFormUpdate;
import br.com.herois.service.UsuarioAdminService;
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
@RequestMapping("/usuarioAdmin")
public class UsuarioAdminController {

    @Autowired
    UsuarioAdminService usuarioAdminService;
    @Autowired
    TokenService tokenService;

    @GetMapping
    private ResponseEntity<List<UsuarioAdminDto>> findAll(){
        List<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findAll();
        return ResponseEntity.ok(UsuarioAdminDto.converterList(usuarioAdmin));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id, @RequestHeader String authorization) {
        if(!tokenService.sameId(authorization, id)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findById(id);
        if(!usuarioAdmin.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UsuarioAdminDto(usuarioAdmin.get()));
    }

    @PostMapping
    @Transactional
    private ResponseEntity<?> save(@RequestBody @Valid UsuarioAdminForm form, UriComponentsBuilder uriBuilder){
        try{
            UsuarioAdmin usuarioAdmin = usuarioAdminService.insert(form);
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioAdmin.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioAdminDto(usuarioAdmin));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("AuthenticationException", e.getMessage()));
        } catch (EmailExistenteException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Email", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Transactional
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UsuarioAdminFormUpdate usuarioAdminFormUpdate, @RequestHeader String authorization){
        if(!tokenService.sameId(authorization, id)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findById(id);
        if(!usuarioAdmin.isPresent()){
            return ResponseEntity.notFound().build();
        }
        UsuarioAdmin usuarioAdminUpdated = usuarioAdminService.update(usuarioAdminFormUpdate, usuarioAdmin);
        return ResponseEntity.ok(new UsuarioAdminDto(usuarioAdminUpdated));
    }

    @DeleteMapping("/{id}")
    @Transactional
    private ResponseEntity<?> deleteById(@PathVariable Long id, @RequestHeader String authorization){
        if(!tokenService.sameId(authorization, id)){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Token inválido", "Token não coincide com o usuário"));
        }
        Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findById(id);
        if(!usuarioAdmin.isPresent()){
            return ResponseEntity.notFound().build();
        }
        usuarioAdminService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
