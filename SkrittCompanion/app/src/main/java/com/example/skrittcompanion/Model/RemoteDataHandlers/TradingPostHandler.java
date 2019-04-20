package com.example.skrittcompanion.Model.RemoteDataHandlers;

import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.Item;
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

public class TradingPostHandler {

    private TradingPostService remoteData;
    private StringBuilder tempIds;
    private MutableLiveData<List<Transaction>> mutableTransactionLiveList;
    private String authKey;
    private List<Transaction> tempTransactionList;

    public TradingPostHandler() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mutableTransactionLiveList =new MutableLiveData<>();
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);
        Retrofit dailyRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        remoteData= dailyRetrofit.create(TradingPostService.class);

    }

    public MutableLiveData<List<Transaction>> getItemsOnSell() throws Exception {
        tempIds =new StringBuilder();
        authKey= AccountSingleton.getInstance().getValue().getProvidedAPIKey();
        Call<List<Transaction>> availableDailiesCall=remoteData.getItemsOnSell(authKey);
        availableDailiesCall.enqueue(new Callback<List<Transaction>>() {

            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {

                if(response.body()!=null) {
                    tempTransactionList=response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        tempIds.append(response.body().get(i).getItem_id()).append(",");
                    }
                    retrieveItemsInList(tempIds.substring(0, tempIds.toString().length()-1));
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>>  call, Throwable t) {
                t.printStackTrace();
            }


        });
        return mutableTransactionLiveList;
    }

    public MutableLiveData<List<Transaction>> getItemsOnBuy() throws Exception {
        tempIds =new StringBuilder();
        authKey= AccountSingleton.getInstance().getValue().getProvidedAPIKey();
        mutableTransactionLiveList =new MutableLiveData<>();
        Call<List<Transaction>> availableDailiesCall=remoteData.getItemsOnBuy(authKey);
        availableDailiesCall.enqueue(new Callback<List<Transaction>>() {

            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {

                if(response.body()!=null&&response.body().size()!=0) {
                    tempTransactionList=response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        tempIds.append(response.body().get(i).getItem_id()).append(",");
                    }
                    retrieveItemsInList(tempIds.substring(0, tempIds.toString().length()-1));
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>>  call, Throwable t) {
                t.printStackTrace();
            }


        });
        return mutableTransactionLiveList;
    }

    private void retrieveItemsInList(String ids) {
        Call<List<Item>> accountCall=remoteData.getItemInfo(ids);
        accountCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                for (int i = 0; i < response.body().size (); i++) {
                    for (int j = 0; j < tempTransactionList.size(); j++) {
                        if(tempTransactionList.get(j).getItem_id()==response.body().get(i).getId()){
                            tempTransactionList.get(j).setItem(response.body().get(i));
                        }
                    }
                }
                mutableTransactionLiveList.postValue(tempTransactionList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
