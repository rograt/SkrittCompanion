package com.example.skrittcompanion.Model;

public class Item {

    private int id;
    private String name;
    private String rarity;
    private String icon;

    public Item(int id, String name, String rarity, String icon) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
