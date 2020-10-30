package br.com.herois.model.form;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Poder;
import br.com.herois.model.entities.Universo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HeroiForm {

    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private Universo universo;
    @NotNull
    @NotBlank
    private List<Poder> poder = new ArrayList<>();

    public Heroi converterHeroi(){
        return new Heroi(nome, universo, poder);
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
}
