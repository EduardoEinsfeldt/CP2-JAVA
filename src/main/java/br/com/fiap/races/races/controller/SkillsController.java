package br.com.fiap.races.races.controller;

import br.com.fiap.races.races.dto.SkillsRequest;
import br.com.fiap.races.races.dto.SkillsResponse;
import br.com.fiap.races.races.model.Races;
import br.com.fiap.races.races.model.Skills;
import br.com.fiap.races.races.repository.RacesRepository;
import br.com.fiap.races.races.repository.SkillsRepository;
import br.com.fiap.races.races.service.SkillsService;
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
@RequestMapping(value = "/skills", produces = {"application/json"})
@Tag(name = "api-skills")
public class SkillsController {

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    RacesRepository racesRepository;

    @Autowired
    SkillsService skillsService;

    @Operation(summary = "Cria uma nova habilidade vinculada a uma raça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidade cadastrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skills.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos ou raça não encontrada",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Skills> createSkill(@Valid @RequestBody SkillsRequest skillRequest) {
        Optional<Races> raca = racesRepository.findById(skillRequest.getRacesId());
        if (raca.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Skills skill = skillsService.requestToSkill(skillRequest, raca.get());
        Skills skillSalva = skillsRepository.save(skill);
        return new ResponseEntity<>(skillSalva, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna uma lista paginada de habilidades")
    @GetMapping
    public ResponseEntity<Page<SkillsResponse>> readSkills(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("nome").ascending());
        return new ResponseEntity<>(skillsService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna uma habilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidade encontrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SkillsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SkillsResponse> readSkill(@PathVariable Long id) {
        Optional<Skills> skill = skillsRepository.findById(id);
        if (skill.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skillsService.skillToResponse(skill.get()), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma habilidade existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidade atualizada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skills.class))}),
            @ApiResponse(responseCode = "400", description = "Habilidade não encontrada para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable Long id,
                                              @Valid @RequestBody SkillsRequest request) {
        Optional<Skills> skillExistente = skillsRepository.findById(id);
        Optional<Races> race = racesRepository.findById(request.getRacesId());

        if (skillExistente.isEmpty() || race.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Skills skillAtualizada = new Skills(
                id,
                request.getNome(),
                request.getDescricao(),
                request.getTipo(),
                race.get()
        );

        Skills skillSalva = skillsRepository.save(skillAtualizada);
        return new ResponseEntity<>(skillSalva, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma habilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Habilidade não encontrada para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Habilidade excluída com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        Optional<Skills> skillExistente = skillsRepository.findById(id);
        if (skillExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        skillsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
