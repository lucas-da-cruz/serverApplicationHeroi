package br.com.herois.model.dto;

import br.com.herois.model.entities.UsuarioAdmin;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdminDto {

    private Long id;
    private String nome;
    private String email;

    public UsuarioAdminDto() {
    }

    public UsuarioAdminDto(UsuarioAdmin usuarioAdmin) {
        id = usuarioAdmin.getId();
        nome = usuarioAdmin.getNome();
        email = usuarioAdmin.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UsuarioAdminDto> converterList(List<UsuarioAdmin> usuarioAdmin) {
        List<UsuarioAdminDto> usuarioAdminDto = new ArrayList<>();
        usuarioAdmin.forEach(a -> {
            usuarioAdminDto.add(new UsuarioAdminDto(a));
        });
        return usuarioAdminDto;
    }
}
