package br.com.fiap.races.races.service;

import br.com.fiap.races.races.controller.RacesController;
import br.com.fiap.races.races.dto.RacesRequest;
import br.com.fiap.races.races.dto.RacesResponse;
import br.com.fiap.races.races.model.Races;
import br.com.fiap.races.races.repository.RacesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class RacesService {

    private final RacesRepository racesRepository;

    public RacesService(RacesRepository racesRepository) {
        this.racesRepository = racesRepository;
    }

    public Races requestToRace(RacesRequest request) {
        return new Races(
                null,
                request.getNome(),
                request.getExpectativaDeVida(),
                request.getOrigem(),
                request.getDescricao(),
                request.getCorSangue()
        );
    }

    public RacesResponse raceToResponse(Races race) {
        Link link = linkTo(
                methodOn(RacesController.class).readRace(race.getId())
        ).withSelfRel();

        return new RacesResponse(race.getId(), race.getNome(), race.getExpectativaDeVida(), race.getOrigem(), race.getDescricao(), race.getCorSangue(), link);
    }

    public List<RacesResponse> racesToResponse(List<Races> races) {
        List<RacesResponse> lista = new ArrayList<>();
        for (Races r : races) {
            lista.add(raceToResponse(r));
        }
        return lista;
    }

    public Page<RacesResponse> findAll(Pageable pageable) {
        return racesRepository.findAll(pageable)
                .map(this::raceToResponse);
    }
}
