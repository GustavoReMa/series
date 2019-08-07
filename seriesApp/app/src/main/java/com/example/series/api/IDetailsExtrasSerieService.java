package com.example.series.api;

import com.example.series.model.entity.SerieDetailExtras;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDetailsExtrasSerieService {

    @GET(".")
    Call<SerieDetailExtras> getDetailsExtrasSerie(@Query("i") String imdb, @Query("apikey") String apikey, @Query("plot") String plot);

}
