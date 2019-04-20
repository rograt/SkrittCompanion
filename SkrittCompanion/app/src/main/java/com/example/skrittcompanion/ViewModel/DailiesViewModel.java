package com.example.skrittcompanion.ViewModel;

import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.Model.Repositories.DailyRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DailiesViewModel extends ViewModel {
    private DailyRepository repo;

    public DailiesViewModel() {
        repo= DailyRepository.getInstance();
    }


    public MutableLiveData<List<DailyInfo>> getFractalDailies() throws Exception {
        return repo.getFractals();
    }

    public MutableLiveData<List<DailyInfo>> getPveDailies() throws Exception {
        return repo.getPveDailies();
    }

    public MutableLiveData<List<DailyInfo>> getPvpDailies() throws Exception {
        return repo.getPvpDailies();
    }

    public MutableLiveData<List<DailyInfo>> getWvwDailies() throws Exception {
        return repo.getWvwDailies();
    }


}
