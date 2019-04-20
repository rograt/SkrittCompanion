package com.example.skrittcompanion.Model.Repositories;

import android.content.Context;

import com.example.skrittcompanion.Model.CurrencyInfo;
import com.example.skrittcompanion.Model.DAOs.BossDAO;
import com.example.skrittcompanion.Model.WorldBoss;
import com.example.skrittcompanion.Utils.SpawnTimeConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {WorldBoss.class}, version = 1)
@TypeConverters({SpawnTimeConverter.class})
public abstract class SkrittDB extends RoomDatabase {

    public abstract BossDAO bossDAO();

    private static volatile SkrittDB INSTANCE;

    static SkrittDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SkrittDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SkrittDB.class, "word_database").build();
                }
            }
        }
        return INSTANCE;
    }


}
