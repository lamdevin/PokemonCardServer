package com.lamdevin.PokemonCardServer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamdevin.PokemonCardServer.models.PokemonCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonCardListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PokemonCardListController pokemonCardListController;

    @Test
    void contextLoads() {
        assertNotNull(pokemonCardListController);
        assertNotNull(mockMvc);
    }

    @Test
    void testGetAllPokemonCards() throws Exception {
        List<PokemonCard> cards = pokemonCardListController.getCards();
        String cardsString = new ObjectMapper().writeValueAsString(cards);
        mockMvc.perform(
                        get("/api/pokemon/all"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().json(cardsString));
    }
}