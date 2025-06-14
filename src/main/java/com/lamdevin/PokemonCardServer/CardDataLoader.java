package com.lamdevin.PokemonCardServer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.repositories.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CardDataLoader implements CommandLineRunner {

    private final CardRepository cardRepository;
    private final ObjectMapper objectMapper;

    public CardDataLoader(CardRepository cardRepository, ObjectMapper objectMapper) {
        this.cardRepository = cardRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cardRepository.count() == 0) {
            InputStream inputStream = new ClassPathResource("data/defaults.json").getInputStream();
            List<PokemonCard> cards = objectMapper.readValue(inputStream, new TypeReference<>() {});
            cardRepository.saveAll(cards);
            System.out.println("Loaded default cards.");
        }
    }
}
