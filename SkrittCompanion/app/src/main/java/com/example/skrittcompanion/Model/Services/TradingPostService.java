package com.example.skrittcompanion.Model.Services;

import com.example.skrittcompanion.Model.Item;
import com.example.skrittcompanion.Model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TradingPostService {
    @GET("/v2/commerce/transactions/current/sells")
    Call<List<Transaction>> getItemsOnSell(@Query("access_token") String key);


    @GET("/v2/commerce/transactions/current/buys")
    Call<List<Transaction>> getItemsOnBuy(@Query("access_token") String key);

    @GET("/v2/items")
    Call<List<Item>> getItemInfo(@Query("ids") String ids);

}
