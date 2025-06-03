package com.lamdevin.PokemonCardServer.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lamdevin.PokemonCardServer.models.PokemonCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
        File defaults = new File(PATH_TO_DEFAULTS);
        cards = new ArrayList<>();
        readFromJSONFile(cardsJSON);
        if (cards.isEmpty()) {
            cardsJSON = new File(PATH_TO_DEFAULTS);
            readFromJSONFile(defaults);
            updateJSONFile();
        }
    }

    public List<PokemonCard> getAllCards() {
        readFromJSONFile(cardsJSON);
        return cards;
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
