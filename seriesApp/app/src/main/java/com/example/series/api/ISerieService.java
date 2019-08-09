package com.example.series.api;

import com.example.series.model.entity.ActorData;
import com.example.series.model.entity.EpisodeData;
import com.example.series.model.entity.SerieData;
import com.example.series.model.entity.SerieDetailData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ISerieService {
    @GET("search/series")
    Call<SerieData> getSeries(@Header("Authorization") String authorization, @Query("name") String name);

    @GET("series/{id}")
    Call<SerieDetailData> getSerieDetail(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("series/{id}/episodes/query")
    Call<EpisodeData> getEpisodes(@Header("Authorization") String authorization, @Path("id") int id, @Query("airedSeason") int season);

    @GET("series/{id}/actors")
    Call<ActorData> getActors(@Header("Authorization") String authorization, @Path("id") int id);
}
