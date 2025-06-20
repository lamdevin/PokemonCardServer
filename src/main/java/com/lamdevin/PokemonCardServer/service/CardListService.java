package com.lamdevin.PokemonCardServer.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lamdevin.PokemonCardServer.CardDataLoader;
import com.lamdevin.PokemonCardServer.models.PokemonCard;
import com.lamdevin.PokemonCardServer.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages a list of Pokemon Cards.
 */
@Service
public class CardListService {

    private final CardRepository repository;
    private final CardDataLoader cardDataLoader;

    private static final String PATH_TO_JSON = "/tmp/cards.json";
    private static final String PATH_TO_DEFAULTS = "src/main/resources/data/defaults.json";

    private File cardsJSON;
    private List<PokemonCard> cards;

    public CardListService(CardRepository repository, CardDataLoader cardDataLoader) {
        this.repository = repository;
        this.cardDataLoader = cardDataLoader;
    }

    public List<PokemonCard> getAllCards() {
        return repository.findAll();
    }

    public PokemonCard getCardFromId(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon Card with ID " + id + " not found."));
    }

    public void addCard(PokemonCard newCard) {
        repository.save(newCard);
    }

    public PokemonCard updateCard(long id, PokemonCard newCard) throws EntityNotFoundException {
        PokemonCard pc = getCardFromId(id);
        pc.setName(newCard.getName());
        pc.setType(newCard.getType());
        pc.setRarity(newCard.getRarity());
        pc.setPicture_url(newCard.getPicture_url());
        pc.setHp(newCard.getHp());

        return repository.save(pc);
    }

    public PokemonCard deleteCard(long id) throws EntityNotFoundException {
        PokemonCard card = getCardFromId(id);
        repository.deleteById(id);
        return card;
    }

    public void resetToDefaults() throws IOException {
        cardDataLoader.loadDefaults();
    }


    /**
     * Read card data from JSON file
     * @param jsonFile JSON file of card data
     */
    private void readFromJSONFile(File jsonFile) {
        Gson gson = new Gson();
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(jsonFile));
            PokemonCard[] cardArray = gson.fromJson(jsonReader, PokemonCard[].class);
            cards = new ArrayList<>(Arrays.asList(cardArray));
        } catch (FileNotFoundException e) {
            System.out.println(jsonFile.getPath() + " not found.");
        } catch (Exception e) {
            System.out.println("Error reading from " + jsonFile.getPath());
        }
    }

    /**
     * Write card data to JSON file
     */
    private void updateJSONFile() {
        Gson gson = new Gson();

        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(PATH_TO_JSON));
            PokemonCard[] cardArray = cards.toArray(new PokemonCard[0]);
            gson.toJson(cardArray, PokemonCard[].class, jsonWriter);
            jsonWriter.flush();
            jsonWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to " + PATH_TO_JSON);
        }

    }
}
