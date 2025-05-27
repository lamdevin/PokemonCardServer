package com.lamdevin.PokemonCardServer.service;

import com.lamdevin.PokemonCardServer.models.PokemonCard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of Pokemon Cards.
 */
public class CardListService {
    private static final String PATH_TO_JSON = "data/cards.json";
    private static final String PATH_TO_DEFAULTS = "src/main/resources/static/defaults.json";
    private File cardsJSON;
    private List<PokemonCard> cards;

    public CardListService() {
        cardsJSON = new File(PATH_TO_JSON);
        cards = new ArrayList<>();
        // read from JSON, update cards
        if (cards.isEmpty()) {
            cardsJSON = new File(PATH_TO_DEFAULTS);
            //read from defaults, update cards, write to json file
        }
    }


}
