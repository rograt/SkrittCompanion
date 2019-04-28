package com.example.skrittcompanion.Model.RemoteDataHandlers;

import com.example.skrittcompanion.Model.DailyGroup;
import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.Model.Services.DailyService;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyHandler {

    private DailyService remoteData;

    private StringBuilder fractalIds;
    private StringBuilder wvwIds;
    private StringBuilder pvpIds;
    private StringBuilder pveIds;

    private MutableLiveData<List<DailyInfo>> fractalInfo;
    private MutableLiveData<List<DailyInfo>> wvwInfo;
    private MutableLiveData<List<DailyInfo>> pvpInfo;
    private MutableLiveData<List<DailyInfo>> pveInfo;

    // Only used during special events.
    private MutableLiveData<List<DailyInfo>> specialInfo;


    public DailyHandler() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        fractalInfo=new MutableLiveData<>();
        wvwInfo=new MutableLiveData<>();
        pvpInfo=new MutableLiveData<>();
        pveInfo=new MutableLiveData<>();
        specialInfo=new MutableLiveData<>();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);
        Retrofit dailyRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        remoteData= dailyRetrofit.create(DailyService.class);
    }

    public MutableLiveData<List<DailyInfo>> getFractals() throws Exception {

        fractalIds =new StringBuilder();
        Call<DailyGroup> availableDailiesCall=remoteData.getDailies();
        availableDailiesCall.enqueue(new Callback<DailyGroup>() {

            @Override
            public void onResponse(Call<DailyGroup> call, Response<DailyGroup> response) {

                if(response.body()!=null) {
                    for (int i = 0; i < response.body().getFractals().length; i++) {
                        fractalIds.append(response.body().getFractals()[i].getId()).append(",");
                    }
                    retrieveFractalInfo(fractalIds.substring(0, fractalIds.toString().length()-1));
                }
            }

            @Override
            public void onFailure(Call<DailyGroup> call, Throwable t) {
                t.printStackTrace();
            }


        });
        return fractalInfo;
    }


    private void retrieveFractalInfo(String ids) {
        Call<List<DailyInfo>> accountCall=remoteData.getDailyInfo(ids);
        accountCall.enqueue(new Callback<List<DailyInfo>>() {
            @Override
            public void onResponse(Call<List<DailyInfo>> call, Response<List<DailyInfo>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    fractalInfo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DailyInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }



    public MutableLiveData<List<DailyInfo>> getPveDailies() throws Exception {
        pveIds =new StringBuilder();
        Call<DailyGroup> availableDailiesCall=remoteData.getDailies();
        availableDailiesCall.enqueue(new Callback<DailyGroup>() {
            @Override
            public void onResponse(Call<DailyGroup> call, Response<DailyGroup> response) {

                if(response.body()!=null) {
                    for (int i = 0; i < response.body().getPve().length; i++) {
                        pveIds.append(response.body().getPve()[i].getId()).append(",");
                    }
                    getPveDailyInfo(pveIds.substring(0, pveIds.toString().length()-1));
                }
            }
            @Override
            public void onFailure(Call<DailyGroup> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return pveInfo;
    }


    private void getPveDailyInfo(String ids) {
        Call<List<DailyInfo>> accountCall=remoteData.getDailyInfo(ids);
        accountCall.enqueue(new Callback<List<DailyInfo>>() {
            @Override
            public void onResponse(Call<List<DailyInfo>> call, Response<List<DailyInfo>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    pveInfo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DailyInfo>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }



    public MutableLiveData<List<DailyInfo>> getPvpDailies() throws Exception {
        pvpIds =new StringBuilder();
        Call<DailyGroup> availableDailiesCall=remoteData.getDailies();
        availableDailiesCall.enqueue(new Callback<DailyGroup>() {
            @Override
            public void onResponse(Call<DailyGroup> call, Response<DailyGroup> response) {

                if(response.body()!=null) {
                    for (int i = 0; i < response.body().getPvp().length; i++) {
                        pvpIds.append(response.body().getPvp()[i].getId()).append(",");
                    }
                    retrievePvpDailyInfo(pvpIds.substring(0, pvpIds.toString().length()-1));
                }
            }
            @Override
            public void onFailure(Call<DailyGroup> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return pvpInfo;
    }


    private void retrievePvpDailyInfo(String ids) {
        Call<List<DailyInfo>> accountCall=remoteData.getDailyInfo(ids);
        accountCall.enqueue(new Callback<List<DailyInfo>>() {
            @Override
            public void onResponse(Call<List<DailyInfo>> call, Response<List<DailyInfo>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    pvpInfo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DailyInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public MutableLiveData<List<DailyInfo>> getWvwDailies() throws Exception {
        wvwIds =new StringBuilder();
        Call<DailyGroup> availableDailiesCall=remoteData.getDailies();
        availableDailiesCall.enqueue(new Callback<DailyGroup>() {
            @Override
            public void onResponse(Call<DailyGroup> call, Response<DailyGroup> response) {

                if(response.body()!=null) {
                    for (int i = 0; i < response.body().getWvw().length; i++) {
                        wvwIds.append(response.body().getWvw()[i].getId()).append(",");
                    }
                    retrieveWvwDailyInfo(wvwIds.substring(0, wvwIds.toString().length()-1));
                }
            }
            @Override
            public void onFailure(Call<DailyGroup> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return wvwInfo;
    }


    private void retrieveWvwDailyInfo(String ids) {
        Call<List<DailyInfo>> accountCall=remoteData.getDailyInfo(ids);
        accountCall.enqueue(new Callback<List<DailyInfo>>() {

            @Override
            public void onResponse(Call<List<DailyInfo>> call, Response<List<DailyInfo>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    wvwInfo.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<DailyInfo>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }


}
