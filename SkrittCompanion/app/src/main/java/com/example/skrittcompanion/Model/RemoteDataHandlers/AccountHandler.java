package com.example.skrittcompanion.Model.RemoteDataHandlers;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.Services.AccountService;
import com.example.skrittcompanion.Model.Services.TradingPostService;
import com.example.skrittcompanion.Model.Transaction;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountHandler {
    AccountService service;

    public AccountHandler(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service= retrofit.create(AccountService.class);
    }

    public void getAccountInfo(final String authKey){
        Call<Account> accountCall=service.getAccountInfo(authKey);
        accountCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {

                if(response.body()!=null) {
                    Account account=response.body();
                    account.setProvidedAPIKey(authKey);
                    AccountSingleton.CreateAccount(account);
                }
            }

            @Override
            public void onFailure(Call<Account>  call, Throwable t) {
                t.printStackTrace();
            }


        });
    }
}
