package br.com.herois.model.dto;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.Poder;
import br.com.herois.model.entities.Universo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HeroiDto {

    private Long id;
    private String nome;
    private Universo universo;
    private List<Poder> poder = new ArrayList<>();
    private Boolean status;
    private String dataCadastrada;

    public HeroiDto() {
    }

    public HeroiDto(Heroi heroi) {
        id = heroi.getId();
        nome = heroi.getNome();
        universo = heroi.getUniverso();
        poder = heroi.getPoder();
        status = heroi.getStatus();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataCadastrada = heroi.getDataCadastrada().format(formatoData);
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

    public String getDataCadastrada() {
        return dataCadastrada;
    }

    public void setDataCadastrada(String dataCadastrada) {
        this.dataCadastrada = dataCadastrada;
    }
}
