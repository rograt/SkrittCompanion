package com.example.skrittcompanion.Model;

public class DailyInfo {

    private String icon;
    private String name;

    public DailyInfo(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DailyInfo{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
