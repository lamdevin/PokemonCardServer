package com.lamdevin.PokemonCardServer.repositories;

import com.lamdevin.PokemonCardServer.models.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<PokemonCard, Long> {
}
