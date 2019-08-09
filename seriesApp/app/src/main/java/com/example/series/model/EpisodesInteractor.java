package com.example.series.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.series.api.ISerieService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.IEpisodesSerie;
import com.example.series.model.entity.EpisodeData;
import com.example.series.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesInteractor implements IEpisodesSerie.model{

    private static final String TAG = "EpisodesInteractor";
    private IEpisodesSerie.presenter iEpisodesSeriePresenter;
    private Context ctx;
    private String token = "";

    public EpisodesInteractor(IEpisodesSerie.presenter iEpisodesSeriePresenter,Context ctx){
        this.iEpisodesSeriePresenter = iEpisodesSeriePresenter;
        this.ctx = ctx;
    }

    @Override
    public void getEpisodesSerieApi(int id, int season) {
        getToken();
        Log.e(TAG,"Token recuperado: " + token);
        ISerieService service = ServiceClient.createSerieService();
        Call<EpisodeData> episodeDataCall = service.getEpisodes("Bearer " + token,id,season);
        episodeDataCall.enqueue(new Callback<EpisodeData>() {
            @Override
            public void onResponse(Call<EpisodeData> call, Response<EpisodeData> response) {
                Gson gson = new Gson();
                if(response.code()==200){
                    Log.e(TAG,">>>>Response episodesSerie>>>>" + gson.toJson(response.body().getData()));
                    iEpisodesSeriePresenter.showEpisodesSerie(response.body());
                }else{
                    if (response.code() == 401) {
                        iEpisodesSeriePresenter.showErrorEpisodes("Not authorized");

                    } else {
                        if (response.code() == 404) {
                            iEpisodesSeriePresenter.showErrorEpisodes("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<EpisodeData> call, Throwable t) {

            }
        });
    }

    private void getToken() {
        SharedPreferences mPrefs = ctx.getSharedPreferences(Constants.NAME_PREFS, Context.MODE_PRIVATE);
        token = mPrefs.getString("token", null);
    }
}
