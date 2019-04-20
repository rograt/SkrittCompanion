package com.example.skrittcompanion.Model.Services;

import com.example.skrittcompanion.Model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountService {

    @GET("/v2/account")
    Call<Account> getAccountInfo(@Query("access_token") String authKey);

}
