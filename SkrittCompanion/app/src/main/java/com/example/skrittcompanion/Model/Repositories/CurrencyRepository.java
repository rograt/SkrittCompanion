package com.example.skrittcompanion.Model.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.skrittcompanion.Model.CurrencyInfo;
import com.example.skrittcompanion.Model.RemoteDataHandlers.WalletHandler;
import com.example.skrittcompanion.Model.Wallet;


import androidx.lifecycle.MutableLiveData;

public class CurrencyRepository {

    private static CurrencyRepository repository;
    private WalletHandler handler;
    private final static String IDS="[1,\n" + "  1,\n" + "  2,\n" + "  3,\n" + "  4,\n" + "  5,\n" + "  6,\n" + "  7,\n" + "  9,\n" + "  10,\n" + "  11,\n" + "  12,\n" + "  13,\n" + "  14,\n" + "  15,\n" + "  16,\n" + "  18,\n" + "  19,\n" + "  20,\n" + "  22,\n" + "  23,\n" + "  24,\n" + "  25,\n" + "  26,\n" + "  27,\n" + "  28,\n" + "  29,\n" + "  30,\n" + "  31,\n" + "  32,\n" + "  33,\n" + "  34,\n" + "  35,\n" + "  36,\n" + "  37,\n" + "  38,\n" + "  39,\n" + "  40,\n" + "  41,\n" + "  42,\n" + "  43,\n" + "  44,\n" + "  45,\n" + "  46,\n" + "  47\n" + "47\n"+"]";

    private CurrencyRepository() {
        handler=new WalletHandler();
    }

    public static CurrencyRepository getInstance(){
        if(repository!=null){
            return repository;
        }
        else {
            return repository=new CurrencyRepository();
        }
    }

    public  MutableLiveData<Wallet> getWallet() {
        return handler.getWallet(IDS);
    }

}


