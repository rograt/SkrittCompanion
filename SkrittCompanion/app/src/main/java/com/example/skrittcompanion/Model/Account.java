package com.example.skrittcompanion.Model;

public class Account {
    private String name;
    private int age;
    private int fractal_level;
    private int daily_ap;
    private int monthly_ap;
    private int wvw_rank;
    private transient String providedAPIKey;


    public Account(String name, int age, int fractal_level, int daily_ap, int monthly_ap, int wvw_rank) {
        this.name = name;
        this.age = age;
        this.fractal_level = fractal_level;
        this.daily_ap = daily_ap;
        this.monthly_ap = monthly_ap;
        this.wvw_rank = wvw_rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFractal_level() {
        return fractal_level;
    }

    public void setFractal_level(int fractal_level) {
        this.fractal_level = fractal_level;
    }

    public int getDaily_ap() {
        return daily_ap;
    }

    public void setDaily_ap(int daily_ap) {
        this.daily_ap = daily_ap;
    }

    public int getMonthly_ap() {
        return monthly_ap;
    }

    public void setMonthly_ap(int monthly_ap) {
        this.monthly_ap = monthly_ap;
    }

    public int getWvw_rank() {
        return wvw_rank;
    }

    public void setWvw_rank(int wvw_rank) {
        this.wvw_rank = wvw_rank;
    }

    public String getProvidedAPIKey() {
        return providedAPIKey;
    }

    public void setProvidedAPIKey(String providedAPIKey) {
        this.providedAPIKey = providedAPIKey;
    }
}
