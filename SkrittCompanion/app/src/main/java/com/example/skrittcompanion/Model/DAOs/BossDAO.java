package com.example.skrittcompanion.Model.DAOs;

import com.example.skrittcompanion.Model.WorldBoss;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BossDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorldBoss boss);

    @Query("SELECT * from table_boss")
    LiveData<List<WorldBoss>> getAllBosses();
}
