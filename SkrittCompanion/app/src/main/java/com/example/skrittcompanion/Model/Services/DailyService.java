package com.example.skrittcompanion.Model.Services;

import com.example.skrittcompanion.Model.DailyGroup;
import com.example.skrittcompanion.Model.DailyInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DailyService {

    @GET("/v2/achievements/daily")
    Call<DailyGroup> getDailies();

    @GET("/v2/achievements")
    Call<List<DailyInfo>> getDailyInfo (@Query("ids") String ids);


}
