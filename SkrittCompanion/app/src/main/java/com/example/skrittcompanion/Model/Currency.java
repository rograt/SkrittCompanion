package com.example.skrittcompanion.Model;

public class Currency {

    private int id;
    private int value;
    private CurrencyInfo description;

    public Currency(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CurrencyInfo getDescription() {
        return description;
    }

    public void setDescription(CurrencyInfo description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }
}
