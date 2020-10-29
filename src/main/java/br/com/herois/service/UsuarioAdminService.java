package br.com.herois.service;

import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.exception.EmailExistenteException;
import br.com.herois.model.form.UsuarioAdminForm;
import br.com.herois.model.form.UsuarioAdminFormUpdate;
import br.com.herois.repository.UsuarioAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class UsuarioAdminService {

    @Autowired
    UsuarioAdminRepository usuarioAdminRepository;

    public List<UsuarioAdmin> findAll() {
        return usuarioAdminRepository.findAll();
    }

    public UsuarioAdmin insert(UsuarioAdminForm form) {
        if (isEmailExist(form.getEmail())) {
            throw new EmailExistenteException("A conta: " + form.getEmail() +  " já é existente!");
        }
        UsuarioAdmin usuarioAdmin = form.converterUsuario();
        //Adicionando hash na senha
        usuarioAdmin.setSenha(new BCryptPasswordEncoder().encode(usuarioAdmin.getSenha()));
        return usuarioAdminRepository.save(usuarioAdmin);
    }

    public Optional<UsuarioAdmin> findById(Long id){
        return usuarioAdminRepository.findById(id);
    }

    public Optional<UsuarioAdmin> findByEmail(String email){
        return usuarioAdminRepository.findByEmail(email);
    }

    public UsuarioAdmin update(UsuarioAdminFormUpdate usuarioAdminFormUpdate, Optional<UsuarioAdmin> usuarioAdmin){
        usuarioAdmin.get().setNome(usuarioAdminFormUpdate.getNome());
        usuarioAdmin.get().setEmail(usuarioAdminFormUpdate.getEmail());
        UsuarioAdmin usuarioAdminUpdated = usuarioAdminRepository.save(usuarioAdmin.get());
        return usuarioAdminUpdated;
    }

    public UsuarioAdmin updatePassword(Optional<UsuarioAdmin> usuarioAdmin, String newPassword){
        usuarioAdmin.get().setSenha(new BCryptPasswordEncoder().encode(newPassword));
        UsuarioAdmin usuarioAdminUpdated = usuarioAdminRepository.save(usuarioAdmin.get());
        return usuarioAdminUpdated;
    }

    public void deleteById(Long id){
        usuarioAdminRepository.deleteById(id);
    }

    public boolean isEmailExist(String email){
        Optional<UsuarioAdmin> usuario = usuarioAdminRepository.findByEmail(email);
        return usuario.isPresent();
    }


}
