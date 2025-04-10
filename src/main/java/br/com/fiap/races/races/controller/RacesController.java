package br.com.fiap.races.races.controller;

import br.com.fiap.races.races.dto.RacesRequest;
import br.com.fiap.races.races.dto.RacesResponse;
import br.com.fiap.races.races.model.Races;
import br.com.fiap.races.races.repository.RacesRepository;
import br.com.fiap.races.races.service.RacesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/races", produces = {"application/json"})
@Tag(name = "api-races")
public class RacesController {

    @Autowired
    RacesRepository racesRepository;

    @Autowired
    RacesService racesService;

    @Operation(summary = "Cria uma nova raça fictícia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Raça cadastrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Races.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Races> createRace(@Valid @RequestBody RacesRequest raceRequest) {
        Races raceSalva = racesRepository.save(racesService.requestToRace(raceRequest));
        return new ResponseEntity<>(raceSalva, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna uma lista paginada de raças fictícias")
    @GetMapping
    public ResponseEntity<Page<RacesResponse>> readRaces(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("nome").ascending());
        return new ResponseEntity<>(racesService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna uma raça fictícia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raça encontrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RacesResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhuma raça encontrada",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RacesResponse> readRaca(@PathVariable Long id) {
        Optional<Races> race = racesRepository.findById(id);
        if (race.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(racesService.raceToResponse(race.get()), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma raça fictícia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Raça encontrada e atualizada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Races.class))}),
            @ApiResponse(responseCode = "400", description = "Nenhuma raça encontrada para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Races> updateRace(@PathVariable Long id,
                                            @RequestBody Races raca) {
        Optional<Races> raceExistente = racesRepository.findById(id);
        if (raceExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        raca.setId(id);
        Races raceAtualizada = racesRepository.save(raca);
        return new ResponseEntity<>(raceAtualizada, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma raça fictícia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhuma raça encontrada para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Raça excluída com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        Optional<Races> raceExistente = racesRepository.findById(id);
        if (raceExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        racesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}