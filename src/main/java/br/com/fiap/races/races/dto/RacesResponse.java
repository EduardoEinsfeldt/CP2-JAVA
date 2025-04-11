package br.com.fiap.races.races.dto;

import br.com.fiap.races.races.model.CorSangue;
import org.springframework.hateoas.Link;


public record RacesResponse(Long id, String nome, int expectativadDeVida, String origem, String descricao, CorSangue corSangue, Link link) {
}
