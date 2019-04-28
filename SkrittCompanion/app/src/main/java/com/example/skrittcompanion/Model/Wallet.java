package com.example.skrittcompanion.Model;

public class Wallet {
    private Currency[] currencies;

    public Wallet(Currency[] currencies) {
        this.currencies = currencies;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }
}
