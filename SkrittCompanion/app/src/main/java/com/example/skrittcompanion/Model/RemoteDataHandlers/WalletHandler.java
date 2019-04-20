package com.example.skrittcompanion.Model.RemoteDataHandlers;

import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.Currency;
import com.example.skrittcompanion.Model.CurrencyInfo;
import com.example.skrittcompanion.Model.Services.WalletService;
import com.example.skrittcompanion.Model.Wallet;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletHandler {

    private WalletService service;
    private MutableLiveData<Wallet> walletMutableLiveData;


    public WalletHandler(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);
        Retrofit walletRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service=walletRetrofit.create(WalletService.class);
    }

    public void setCurrencyInfo(String ids){
        Call<List<CurrencyInfo>> call = service.getAllCurrencies(ids);
            call.enqueue(new Callback<List<CurrencyInfo>>() {
                @Override
                public void onResponse(Call<List<CurrencyInfo>> call, Response<List<CurrencyInfo>> response) {

                    if(response.body()!=null){
                        Wallet tempWallet = walletMutableLiveData.getValue();
                        for (int i = 0; i < tempWallet.getCurrencies().length; i++) {
                            tempWallet.getCurrencies()[i].setDescription(response.body().get(i));
                        }
                        walletMutableLiveData.setValue(tempWallet);
                    }
                    // TODO: use the repository list and display it
                }

                @Override
                public void onFailure(Call<List<CurrencyInfo>> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println("FAILED");                // TODO: handle error
                }
            });
    }


    public MutableLiveData<Wallet> getWallet(final String ids){
        walletMutableLiveData = new MutableLiveData<>();
        Call<Currency[]> call = service.getAccountWallet(AccountSingleton.getInstance().getValue().getProvidedAPIKey());
        call.enqueue(new Callback<Currency[]>() {
            @Override
            public void onResponse(Call<Currency[]> call, Response<Currency[]> response) {

                if(response.body()!=null){
                    StringBuilder tempIds=new StringBuilder();
                    for (int i = 0; i < response.body().length; i++) {
                        tempIds.append(response.body()[i].getId()).append(",");
                    }
                    Wallet tempWallet =new Wallet(response.body());
                    walletMutableLiveData.setValue(tempWallet);
                    setCurrencyInfo(tempIds.substring(0, tempIds.toString().length()-1));
                }
                // TODO: use the repository list and display it
            }

            @Override
            public void onFailure(Call<Currency[]> call, Throwable t) {
                t.printStackTrace();
                System.out.println("FAILED");                // TODO: handle error
            }
        });
        return walletMutableLiveData;
    }

}
