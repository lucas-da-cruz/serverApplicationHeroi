package br.com.herois.model.form;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Poder;
import br.com.herois.model.entities.Universo;
import br.com.herois.model.entities.UsuarioAdmin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HeroiForm {

    @NotBlank
    private String nome;
    @NotNull
    private Universo universo;
    @NotNull
    private List<Poder> poder = new ArrayList<>();
    @NotNull
    private Boolean status;
    private UsuarioAdmin usuarioAdmin;

    public Heroi converterHeroi(){
        return new Heroi(nome, universo, poder, status, usuarioAdmin);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Universo getUniverso() {
        return universo;
    }

    public void setUniverso(Universo universo) {
        this.universo = universo;
    }

    public List<Poder> getPoder() {
        return poder;
    }

    public void setPoder(List<Poder> poder) {
        this.poder = poder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UsuarioAdmin getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(UsuarioAdmin usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }
}
