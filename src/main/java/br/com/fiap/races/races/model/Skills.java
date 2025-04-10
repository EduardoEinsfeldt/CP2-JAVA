package br.com.fiap.races.races.model;

import jakarta.persistence.*;

@Entity
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoSkill tipo;

    @ManyToOne
    @JoinColumn(name = "races_id")
    private Races races;

    public Skills() {
    }

    public Skills(Long id, String nome, String descricao, TipoSkill tipo, Races races) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.races = races;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoSkill getTipo() {
        return tipo;
    }

    public void setTipo(TipoSkill tipo) {
        this.tipo = tipo;
    }

    public Races getRaces() {
        return races;
    }

    public void setRaces(Races races) {
        this.races = races;
    }
}
