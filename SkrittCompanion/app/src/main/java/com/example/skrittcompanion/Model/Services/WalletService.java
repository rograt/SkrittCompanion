package com.example.skrittcompanion.Model.Services;

import com.example.skrittcompanion.Model.Currency;
import com.example.skrittcompanion.Model.CurrencyInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WalletService {
    @GET("/v2/account/wallet")
    Call<Currency[]> getAccountWallet(@Query("access_token") String authKey);

    @GET("/v2/currencies")
    Call<List<CurrencyInfo>> getAllCurrencies(@Query("ids") String ids);
}
