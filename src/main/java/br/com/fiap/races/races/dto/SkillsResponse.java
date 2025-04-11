package br.com.fiap.races.races.dto;

import br.com.fiap.races.races.model.Races;
import br.com.fiap.races.races.model.TipoSkill;
import org.springframework.hateoas.Link;

public record SkillsResponse(Long id, String nome, String descricao, TipoSkill tipo, Races races, Link link, Link linkRaces) {
}