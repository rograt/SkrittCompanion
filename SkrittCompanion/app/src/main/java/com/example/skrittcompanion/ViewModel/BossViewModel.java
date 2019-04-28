package com.example.skrittcompanion.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.skrittcompanion.Model.Repositories.BossRepository;
import com.example.skrittcompanion.Model.WorldBoss;

import java.util.List;

public class BossViewModel extends ViewModel {

    private BossRepository bossRepository;

    public BossViewModel(Application application) {
        this.bossRepository = BossRepository.getInstance(application);
    }

    public LiveData<List<WorldBoss>> getAllBosses(){
        return bossRepository.getBosses();
    }

    public List<WorldBoss> getTimedBosses(List<WorldBoss> bosses){
        return bossRepository.getTimedBosses(bosses);
    }

}
