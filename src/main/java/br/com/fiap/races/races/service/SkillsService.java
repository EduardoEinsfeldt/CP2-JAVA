package br.com.fiap.races.races.service;

import br.com.fiap.races.races.controller.RacesController;
import br.com.fiap.races.races.controller.SkillsController;
import br.com.fiap.races.races.dto.RacesResponse;
import br.com.fiap.races.races.dto.SkillsRequest;
import br.com.fiap.races.races.dto.SkillsResponse;
import br.com.fiap.races.races.model.Races;
import br.com.fiap.races.races.model.Skills;
import br.com.fiap.races.races.repository.SkillsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SkillsService {

    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public Skills requestToSkill(SkillsRequest request, Races race) {
        return new Skills(
                null,
                request.getNome(),
                request.getDescricao(),
                request.getTipo(),
                race
        );
    }

    public SkillsResponse skillToResponse(Skills skill) {
        Races race = skill.getRaces();

        Link linkRaces = linkTo(
                methodOn(RacesController.class).readRace(race.getId())
        ).withSelfRel();


        Link link = linkTo(
                methodOn(SkillsController.class).readSkill(skill.getId())
        ).withSelfRel();



        return new SkillsResponse(skill.getId(), skill.getNome(), skill.getDescricao(), skill.getTipo(), skill.getRaces(), link, linkRaces);
    }

    public List<SkillsResponse> skillsToResponse(List<Skills> skills) {
        List<SkillsResponse> lista = new ArrayList<>();
        for (Skills s : skills) {
            lista.add(skillToResponse(s));
        }
        return lista;
    }

    public Page<SkillsResponse> findAll(Pageable pageable) {
        return skillsRepository.findAll(pageable)
                .map(this::skillToResponse);
    }
}