package br.com.herois.controller;

import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.exception.UpdatePasswordException;
import br.com.herois.model.form.EmailForm;
import br.com.herois.model.form.SenhaForm;
import br.com.herois.service.PasswordService;
import br.com.herois.service.UsuarioAdminService;
import br.com.herois.validation.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PasswordController {

    @Autowired
    PasswordService passwordService;
    @Autowired
    UsuarioAdminService usuarioAdminService;

    /*@PostMapping("/pass")
    public ResponseEntity newPassword(@RequestBody @Valid EmailForm email){
        try{
            if(!usuarioAdminService.isEmailExist(email.getEmail())){
                return ResponseEntity.notFound().build();
            }
            String retorno = passwordService.generateNewPass(email.getEmail());
            return ResponseEntity.ok(retorno);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Email não enviado", e.getMessage()));
        }
    }*/

    @PostMapping("/pass/{id}/{email}/{senha}")
    public ResponseEntity resetPassword(@PathVariable Long id, @PathVariable String email, @PathVariable String senha, @RequestBody @Valid SenhaForm senhaForm){
        try{
            Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findById(id);
            usuarioAdmin.get().setSenha(usuarioAdmin.get().getSenha().replace("/", ""));

            if(usuarioAdmin.get().getEmail().equals(email) && usuarioAdmin.get().getSenha().equals(senha)){
                usuarioAdminService.updatePassword(usuarioAdmin, senhaForm.getSenha());
                return ResponseEntity.ok().build();
            } else {
                throw new UpdatePasswordException("O link é inválido para o usuário");
            }

        }
        catch (UpdatePasswordException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Link inválido", e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Algo inesperado aconteceu", e.getMessage()));
        }
    }
}
