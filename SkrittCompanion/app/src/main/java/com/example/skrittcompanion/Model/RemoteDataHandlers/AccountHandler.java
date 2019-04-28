package com.example.skrittcompanion.Model.RemoteDataHandlers;

import androidx.lifecycle.MutableLiveData;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.ApiKey;
import com.example.skrittcompanion.Model.Services.AccountService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountHandler {
    AccountService service;
    private MutableLiveData<Integer> status;

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

    public MutableLiveData<Integer> validateToken(final String authKey){
        status=new MutableLiveData<>();
        status.setValue(0);
        Call<ApiKey> apiKeyCall=service.getTokenInfo(authKey);
        apiKeyCall.enqueue(new Callback<ApiKey>() {
            @Override
            public void onResponse(Call<ApiKey> call, Response<ApiKey> response) {
                if(response.body()!=null) {
                    if(response.body().getPermissions().length==10){
                        status.setValue(200);
                    }
                    else{
                        status.setValue(400);
                    }
                }
                else{
                    status.setValue(400);
                }
            }
            @Override
            public void onFailure(Call<ApiKey>  call, Throwable t) {
                status.setValue(500);
            }
        });
        return status;
    }
}
