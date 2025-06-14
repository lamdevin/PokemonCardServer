package com.lamdevin.PokemonCardServer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamdevin.PokemonCardServer.models.PokemonCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

//    @Test
//    void testAddAndGetCardsFromId() throws Exception {
//        PokemonCard c1 = new PokemonCard();
//        c1.setName("Toki1");
//        c1.setType("Fire");
//        c1.setRarity(5);
//        c1.setPicture_url("url.com");
//        c1.setHp(100);
//
//        PokemonCard c2 = new PokemonCard();
//        c2.setName("Toki2");
//        c2.setType("Water");
//        c2.setRarity(6);
//        c2.setPicture_url("url.ca");
//        c2.setHp(50);
//
//        MvcResult result1 = mockMvc.perform(
//                        post("/api/pokemon/add")
//                                .content(new ObjectMapper().writeValueAsString(c1))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//        // get assigned id
//        long id1 = pokemonCardListController.getNextId() - 1;
//        c1.setId(id1);
//        String c1String = new ObjectMapper().writeValueAsString(c1);
//        assertEquals(c1String,result1.getResponse().getContentAsString());
//
//        MvcResult result2 = mockMvc.perform(
//                        post("/api/pokemon/add")
//                                .content(new ObjectMapper().writeValueAsString(c2))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//        // get assigned id
//        long id2 = pokemonCardListController.getNextId() - 1;
//        c2.setId(id2);
//        String c2String = new ObjectMapper().writeValueAsString(c2);
//        assertEquals(c2String,result2.getResponse().getContentAsString());
//
//        mockMvc.perform(
//                        get("/api/pokemon/" + id1))
//                .andExpect(status().isOk())
//                .andExpect(content().json(c1String)
//                );
//
//        mockMvc.perform(
//                        get("/api/pokemon/" + id2))
//                .andExpect(status().isOk())
//                .andExpect(content().json(c2String)
//                );
//    }

//    @Test
//    void testUpdateCard() throws Exception {
//        PokemonCard card = new PokemonCard();
//        card.setName("Poke1");
//        card.setType("Fire");
//        card.setRarity(5);
//        card.setPicture_url("url.com");
//        card.setHp(100);
//
//        mockMvc.perform(
//                        post("/api/pokemon/add")
//                                .content(new ObjectMapper().writeValueAsString(card))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated()
//                );
//        // get assigned id
//        long id = pokemonCardListController.getNextId() - 1;
//        card.setId(id);
//
//        // change some attributes
//        card.setName("Poke2");
//        card.setType("Water");
//        card.setRarity(1);
//        card.setHp(5);
//
//        mockMvc.perform(
//                        put("/api/pokemon/edit/"+id)
//                                .content(new ObjectMapper().writeValueAsString(card))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(content().json(new ObjectMapper().writeValueAsString(card))
//                );
//
//    }
//
//    @Test
//    void testDeleteCard() throws Exception {
//        PokemonCard card = new PokemonCard();
//        card.setName("Poke1");
//        card.setType("Fire");
//        card.setRarity(5);
//        card.setPicture_url("url.com");
//        card.setHp(100);
//
//        mockMvc.perform(
//                        post("/api/pokemon/add")
//                                .content(new ObjectMapper().writeValueAsString(card))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated()
//                );
//        // get assigned id
//        long id = pokemonCardListController.getNextId() - 1;
//        card.setId(id);
//
//        mockMvc.perform(
//                        delete("/api/pokemon/"+id))
//                .andExpect(status().isNoContent()
//                );
//
//        // delete again should be 404
//        mockMvc.perform(
//                        delete("/api/pokemon/"+id))
//                .andExpect(status().isNotFound()
//                );
//    }

    @Test
    void test404Responses() throws Exception {
        long id = 987654321;
        // delete card
        mockMvc.perform(delete("/api/pokemon/"+id));

        // test 404
        mockMvc.perform(
                        get("/api/pokemon/"+id))
                .andExpect(status().isNotFound()
                );
        mockMvc.perform(
                        put("/api/pokemon/edit/"+id)
                                .content("{}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()
                );
        mockMvc.perform(
                        delete("/api/pokemon/"+id))
                .andExpect(status().isNotFound()
                );

    }
}