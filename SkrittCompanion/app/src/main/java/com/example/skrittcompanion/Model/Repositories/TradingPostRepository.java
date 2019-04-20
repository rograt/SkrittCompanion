package com.example.skrittcompanion.Model.Repositories;

import com.example.skrittcompanion.Model.RemoteDataHandlers.TradingPostHandler;
import com.example.skrittcompanion.Model.Transaction;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class TradingPostRepository {
    private static TradingPostRepository repo;
    private TradingPostHandler handler;


    private TradingPostRepository() {
        handler=new TradingPostHandler();
    }

    public static TradingPostRepository getInstance(){
        if(repo!=null){
            return repo;
        }
        else return repo=new TradingPostRepository();
    };

    public MutableLiveData<List<Transaction>> getItemsOnSale( ) throws Exception {
        return handler.getItemsOnSell();
    }

    public MutableLiveData<List<Transaction>> getItemsOnBuy() throws Exception {
        return handler.getItemsOnBuy();
    }
}
