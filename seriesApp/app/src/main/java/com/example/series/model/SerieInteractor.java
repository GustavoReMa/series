package com.example.series.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.series.api.ISerieService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.ISerie;
import com.example.series.model.entity.SerieData;
import com.example.series.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieInteractor implements ISerie.model {

    private static final String TAG = "SerieInteractor";
    ISerie.presenter iSeriePresenter;
    Context ctx;
    String token = "", name = "";

    public SerieInteractor(ISerie.presenter iSeriePresenter, Context ctx) {
        this.iSeriePresenter = iSeriePresenter;
        this.ctx = ctx;
    }

    @Override
    public void getSeriesApi(String name) {
        getToken();
        Log.e(TAG, token);
        Log.e(TAG, "Nombre obtenido: " + name);
        ISerieService serieService = ServiceClient.createSerieService();
        Call<SerieData> serieDataCall = serieService.getSeries("Bearer " + token, name);
        serieDataCall.enqueue(new Callback<SerieData>() {
            @Override
            public void onResponse(Call<SerieData> call, Response<SerieData> response) {
                Log.e(TAG, "Código del servicio " + response.code());
                Gson gson = new Gson();
                if (response.code() == 200) {

                    Log.e(TAG, "Response getSeries service: " + gson.toJson(response.body().getData()));
                    iSeriePresenter.showSeries(response.body().getData());
                } else {
                    if (response.code() == 401) {
                        Log.e(TAG, ">>>>" + gson.toJson(response.errorBody()));
                        iSeriePresenter.showErrorApi("No authorized");

                    } else {
                        if (response.code() == 404) {
                            iSeriePresenter.showErrorApi("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SerieData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getToken() {
        SharedPreferences mPrefs = ctx.getSharedPreferences(Constants.NAME_PREFS, Context.MODE_PRIVATE);
        token = mPrefs.getString("token", null);
    }
}
