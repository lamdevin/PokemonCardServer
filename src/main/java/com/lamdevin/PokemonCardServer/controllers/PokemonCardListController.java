package com.lamdevin.PokemonCardServer.controllers;

import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.service.CardListService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/pokemon/all")
    public List<PokemonCard> getCards() {
        return cardListService.getAllCards();
    }

    @GetMapping("/api/pokemon/{id}")
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

    @PostMapping("/api/pokemon/add")
    public PokemonCard addCard(@RequestBody PokemonCard newCard, HttpServletResponse response) {
        while (cardListService.getCardFromId(nextId.get()) != null) {
            nextId.incrementAndGet();
        }
        newCard.setId(nextId.getAndIncrement());
        cardListService.addCard(newCard);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return newCard;
    }

    @PutMapping("/api/pokemon/edit/{id}")
    public PokemonCard updateCard(@PathVariable long id, @RequestBody PokemonCard newCard, HttpServletResponse response) {
        PokemonCard card = cardListService.updateCard(id, newCard);
        if (card == null) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tokimon Card ID " + id + " not found.");
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        return card;
    }

    @DeleteMapping("/api/pokemon/{id}")
    public PokemonCard deleteCard(@PathVariable long id, HttpServletResponse response) {
        PokemonCard deletedCard = cardListService.deleteCard(id);
        if (deletedCard == null) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tokimon Card ID " + id + " not found.");
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return deletedCard;
    }

    @PostConstruct
    public void init() {
        cardListService = new CardListService();
        nextId = new AtomicLong(1);
    }
}
