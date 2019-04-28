package com.example.skrittcompanion.Model.Repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.skrittcompanion.Model.DAOs.BossDAO;
import com.example.skrittcompanion.Model.WorldBoss;

@Database(entities = {WorldBoss.class}, version = 1)
public abstract class SkrittDB extends RoomDatabase {

    public abstract BossDAO bossDAO();

    private static volatile SkrittDB INSTANCE;

    static SkrittDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SkrittDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SkrittDB.class, "skritt_database").build();
                }
            }
        }
        return INSTANCE;
    }


}
