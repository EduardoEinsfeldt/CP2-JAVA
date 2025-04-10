package br.com.fiap.races.races.dto;

import br.com.fiap.races.races.model.TipoSkill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SkillsRequest {

    @NotBlank(message = "O nome da skill é obrigatório")
    @Size(min = 2, max = 100, message = "O nome da skill deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição da skill é obrigatória")
    @Size(min = 5, max = 300, message = "A descrição deve ter entre 5 e 300 caracteres")
    private String descricao;

    @NotNull(message = "O tipo da skill é obrigatório")
    private TipoSkill tipo;

    @NotNull(message = "O ID da raça é obrigatório")
    private Long racesId;

    public SkillsRequest() {
    }

    public SkillsRequest(String nome, String descricao, TipoSkill tipo, Long racesId) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.racesId = racesId;
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

    public Long getRacesId() {
        return racesId;
    }

    public void setRacesId(Long racesId) {
        this.racesId = racesId;
    }
}