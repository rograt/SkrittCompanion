package com.example.skrittcompanion.Model;

import com.google.gson.annotations.SerializedName;

public class DailyGroup {

    private Daily[] fractals;
    private Daily[] pve;
    private Daily[] pvp;
    private Daily[] wvw;
    private Daily[] special;

    public DailyGroup(Daily[] fractals, Daily[] pve, Daily[] pvp, Daily[] wvw, Daily[] special) {
        this.fractals = fractals;
        this.pve = pve;
        this.pvp = pvp;
        this.wvw = wvw;
        this.special = special;
    }


    public Daily[] getPve() {
        return pve;
    }

    public Daily[] getPvp() {
        return pvp;
    }

    public Daily[] getWvw() {
        return wvw;
    }

    public Daily[] getSpecial() {
        return special;
    }

    public Daily[] getFractals() {
        return fractals;
    }
}

