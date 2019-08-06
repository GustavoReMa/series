package com.example.series.api;

import com.example.series.model.entity.SerieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ISerieService {
    @GET("search/series")
    Call<SerieData> getSeries(@Header("Authorization") String authorization, @Query("name") String name);

}
