package com.example.skrittcompanion.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class CurrencyInfo {


    private String name;

    private String icon;

    public CurrencyInfo( String name, String icon) {
        this.name = name;
        this.icon = icon;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}