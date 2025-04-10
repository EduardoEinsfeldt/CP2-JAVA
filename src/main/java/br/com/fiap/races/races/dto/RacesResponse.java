package br.com.fiap.races.races.dto;

import org.springframework.hateoas.Link;

public record RacesResponse(Long id, String nome, Link link) {
}
