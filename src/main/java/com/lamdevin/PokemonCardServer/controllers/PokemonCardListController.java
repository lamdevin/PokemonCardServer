package com.lamdevin.PokemonCardServer.controllers;

import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.service.CardListService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * REST Controller for Pokemon Card List
 */
@RestController
public class PokemonCardListController {
    private AtomicLong nextId;
    private CardListService cardListService;

    @GetMapping("/api/tokimon/all")
    public List<PokemonCard> getCards() {
        return cardListService.getAllCards();
    }

    @GetMapping("/api/tokimon/{id}")
    public PokemonCard getCardFromId(@PathVariable long id, HttpServletResponse response) {
        PokemonCard card = cardListService.getCardFromId(id);
        if (card == null) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Pokemon Card ID " + id + " not found.");
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        return card;
    }



}
