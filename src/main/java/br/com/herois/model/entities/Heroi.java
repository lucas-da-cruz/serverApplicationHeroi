package br.com.herois.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Heroi {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Universo universo;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Poder> poder = new ArrayList<>();
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioAdmin usuarioAdmin;
    private LocalDateTime dataCadastrada;

    public Heroi() {
    }

    public Heroi(String nome, Universo universo, List<Poder> poder, Boolean status, UsuarioAdmin usuarioAdmin, LocalDateTime dataCadastrada) {
        this.nome = nome;
        this.universo = universo;
        this.poder = poder;
        this.status = status;
        this.usuarioAdmin = usuarioAdmin;
        this.dataCadastrada = dataCadastrada;
    }

    public Heroi(String nome, Universo universo, List<Poder> poder, Boolean status) {
        this.nome = nome;
        this.universo = universo;
        this.poder = poder;
        this.status = status;
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

    public UsuarioAdmin getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(UsuarioAdmin usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public LocalDateTime getDataCadastrada() {
        return dataCadastrada;
    }

    public void setDataCadastrada(LocalDateTime dataCadastrada) {
        this.dataCadastrada = dataCadastrada;
    }
}
