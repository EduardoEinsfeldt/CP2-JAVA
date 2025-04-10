package br.com.fiap.races.races.dto;

import br.com.fiap.races.races.model.CorSangue;
import jakarta.validation.constraints.*;

public class RacesRequest {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Min(value = 1, message = "A expectativa de vida deve ser no mínimo 1 ano")
    private int expectativaDeVida;

    @NotBlank(message = "A origem é obrigatória")
    @Size(max = 100, message = "A origem deve ter no máximo 100 caracteres")
    private String origem;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres")
    private String descricao;

    @NotNull(message = "A cor do sangue é obrigatória")
    private CorSangue corSangue;

    public RacesRequest() {
    }

    public RacesRequest(String nome, int expectativaDeVida, String origem, String descricao, CorSangue corSangue) {
        this.nome = nome;
        this.expectativaDeVida = expectativaDeVida;
        this.origem = origem;
        this.descricao = descricao;
        this.corSangue = corSangue;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getExpectativaDeVida() {
        return expectativaDeVida;
    }

    public void setExpectativaDeVida(int expectativaDeVida) {
        this.expectativaDeVida = expectativaDeVida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CorSangue getCorSangue() {
        return corSangue;
    }

    public void setCorSangue(CorSangue corSangue) {
        this.corSangue = corSangue;
    }
}