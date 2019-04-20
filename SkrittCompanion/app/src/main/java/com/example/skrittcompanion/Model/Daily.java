package com.example.skrittcompanion.Model;


public class Daily {
    //You might be thinking why in tarnation is this a standalone class if it only has one int
    //Fair enough, looks stupid however it's for future-proofing as the guildwars2 api has extra
    //stuff involved with the Daily and due to the scope of the project at it's current form
    //I've only pulled the id.
    private int id;

    public Daily(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
