package com.example.skrittcompanion.ViewModel;

import android.app.Application;

import com.example.skrittcompanion.Model.Repositories.CurrencyRepository;
import com.example.skrittcompanion.Model.Wallet;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WalletViewModel extends AndroidViewModel {
    private CurrencyRepository currencyRepository;

    public WalletViewModel(Application application) {
        super(application);
        currencyRepository =  CurrencyRepository.getInstance();
    }


    public LiveData<Wallet> getWallet() { return currencyRepository.getWallet(); }

}
