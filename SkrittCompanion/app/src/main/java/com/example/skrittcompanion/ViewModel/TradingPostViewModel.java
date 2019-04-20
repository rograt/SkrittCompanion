package com.example.skrittcompanion.ViewModel;


import android.app.Application;

import com.example.skrittcompanion.Model.Repositories.TradingPostRepository;
import com.example.skrittcompanion.Model.Transaction;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TradingPostViewModel extends AndroidViewModel {
    private TradingPostRepository repository;

    public TradingPostViewModel(@NonNull Application application) {
        super(application);
        repository = TradingPostRepository.getInstance();
    }

    public MutableLiveData<List<Transaction>> getItemsOnSale() throws Exception {
        return repository.getItemsOnSale();
    }

    public MutableLiveData<List<Transaction>> getItemsOnBuy() throws Exception {
        return repository.getItemsOnBuy();
    }

}