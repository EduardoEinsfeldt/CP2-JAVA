package br.com.fiap.races.races.repository;

import br.com.fiap.races.races.model.Races;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacesRepository extends JpaRepository<Races, Long> {
}