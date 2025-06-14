package com.lamdevin.PokemonCardServer.controllers;

import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.service.CardListService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Pokemon Card List
 */
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://lamdevin.github.io"
})
@RestController
public class PokemonCardListController {
    private final CardListService cardListService;

    public PokemonCardListController(CardListService cardListService) {
        this.cardListService = cardListService;
    }

    @GetMapping("/api/pokemon/all")
    public List<PokemonCard> getCards() {
        return cardListService.getAllCards();
    }

    @GetMapping("/api/pokemon/{id}")
    public PokemonCard getCardFromId(@PathVariable long id) {
        return cardListService.getCardFromId(id);
    }

    @PostMapping("/api/pokemon/add")
    public PokemonCard addCard(@RequestBody PokemonCard newCard, HttpServletResponse response) {
        newCard.setId(0);
        cardListService.addCard(newCard);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return newCard;
    }

    @PutMapping("/api/pokemon/edit/{id}")
    public PokemonCard updateCard(@PathVariable long id, @RequestBody PokemonCard newCard) {
        return cardListService.updateCard(id, newCard);
    }

    @DeleteMapping("/api/pokemon/{id}")
    public PokemonCard deleteCard(@PathVariable long id) {
        return cardListService.deleteCard(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleNotFound(EntityNotFoundException ex) {
        return ex.getMessage();
    }


}
