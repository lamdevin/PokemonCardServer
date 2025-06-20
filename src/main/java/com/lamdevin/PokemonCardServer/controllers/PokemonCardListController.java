package com.lamdevin.PokemonCardServer.controllers;

import com.lamdevin.PokemonCardServer.CardDataLoader;
import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.service.CardListService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private final CardDataLoader cardDataLoader;

    public PokemonCardListController(CardListService cardListService, CardDataLoader cardDataLoader) {
        this.cardListService = cardListService;
        this.cardDataLoader = cardDataLoader;
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

    @PostMapping("/api/pokemon/reset")
    public ResponseEntity<String> resetCards() throws IOException {
        cardListService.resetToDefaults();
        return ResponseEntity.ok("Card list reset to defaults.");
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleNotFound(EntityNotFoundException ex) {
        return ex.getMessage();
    }


}
