package com.lamdevin.PokemonCardServer.models;

/**
 * Represents a Pokemon Card, each with an ID, name, type, rarity, picture URL and HP (Health Points)
 */
public class PokemonCard {
    private long id;
    private String name;
    private String type;
    private int rarity;
    private String picture_url;
    private int hp;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setId(long id) {
        this.id = id;
    }
}
