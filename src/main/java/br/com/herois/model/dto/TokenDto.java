package br.com.herois.model.dto;

import java.util.List;

public class TokenDto {
    private String token;
    private String tipo;
    private String nome;
    private List<String> authorities;

    public TokenDto() {
    }

    public TokenDto(String token, String tipo, List<String> authorities) {
        this.token = token;
        this.tipo = tipo;
        this.authorities = authorities;
    }

    public TokenDto(String token, String tipo, List<String> authorities, String nome) {
        this.token = token;
        this.tipo = tipo;
        this.nome = nome;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
