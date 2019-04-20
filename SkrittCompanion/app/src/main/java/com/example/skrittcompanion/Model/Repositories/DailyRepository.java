package com.example.skrittcompanion.Model.Repositories;

import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.Model.RemoteDataHandlers.DailyHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DailyRepository {

    private static DailyRepository repository;

    private DailyHandler remoteData;


    private DailyRepository() {
        remoteData=new DailyHandler();
    }

    public static DailyRepository getInstance(){
        if(repository!=null){
            return repository;
        }
        return repository=new DailyRepository();
    }

    public MutableLiveData<List<DailyInfo>> getFractals() throws Exception {
        return remoteData.getFractals();
    }

    public MutableLiveData<List<DailyInfo>> getPveDailies() throws Exception {
        return remoteData.getPveDailies();
    }

    public MutableLiveData<List<DailyInfo>> getPvpDailies() throws Exception {
        return remoteData.getPvpDailies();
    }

    public MutableLiveData<List<DailyInfo>> getWvwDailies() throws Exception {
        return remoteData.getWvwDailies();
    }


}