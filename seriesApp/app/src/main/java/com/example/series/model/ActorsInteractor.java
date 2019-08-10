package com.example.series.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.series.api.ISerieService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.IActorSerie;
import com.example.series.model.entity.ActorData;
import com.example.series.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorsInteractor implements IActorSerie.model {

    private static final String TAG = "ActorsInteractor";
    private IActorSerie.presenter iActorSeriePresenter;
    private Context ctx;
    private String token;

    public ActorsInteractor(IActorSerie.presenter iActorSeriePresenter, Context ctx) {
        this.iActorSeriePresenter = iActorSeriePresenter;
        this.ctx = ctx;
    }

    @Override
    public void getActorSerieApi(int id) {
        getToken();
        ISerieService iSerieService = ServiceClient.createSerieService();
        Call<ActorData> actorDataCall = iSerieService.getActors("Bearer " + token, id);
        actorDataCall.enqueue(new Callback<ActorData>() {

            @Override
            public void onResponse(Call<ActorData> call, Response<ActorData> response) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    Log.e(TAG, ">>>>>Response actors>>>>> " + gson.toJson(response.body()));
                    iActorSeriePresenter.showActorSerie(response.body());
                } else {
                    if (response.code() == 401) {
                        iActorSeriePresenter.showErrorActor("Not authorized");

                    } else {
                        if (response.code() == 404) {
                            iActorSeriePresenter.showErrorActor("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ActorData> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void getToken() {
        SharedPreferences mPrefs = ctx.getSharedPreferences(Constants.NAME_PREFS, Context.MODE_PRIVATE);
        token = mPrefs.getString("token", null);
    }
}
