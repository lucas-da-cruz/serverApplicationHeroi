package br.com.herois.model.dto;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Universo;

public class HeroiTabelaDto {
    private Long id;
    private String nome;
    private Universo universo;

    public HeroiTabelaDto() {
    }

    public HeroiTabelaDto(Heroi heroi) {
        id = heroi.getId();
        nome = heroi.getNome();
        universo = heroi.getUniverso();
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

    public Universo getUniverso() {
        return universo;
    }

    public void setUniverso(Universo universo) {
        this.universo = universo;
    }
}
