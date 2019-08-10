package com.example.series.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.series.api.IDetailsExtrasSerieService;
import com.example.series.api.ISerieService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.IDetailSerie;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailData;
import com.example.series.model.entity.SerieDetailExtras;
import com.example.series.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsSerieInteractor implements IDetailSerie.model {

    private static final String TAG = "DetailsSerieInteractor";
    private IDetailSerie.presenter iSerieDetailPresenter;
    private SerieDetail serieDetail;
    private SerieDetailExtras serieDetailExtras;
    private Context ctx;
    private String token, imbdId;

    public DetailsSerieInteractor(IDetailSerie.presenter iSerieDetailPresenter, Context ctx) {
        this.iSerieDetailPresenter = iSerieDetailPresenter;
        this.ctx = ctx;
        token = "";
        imbdId = "";
    }


    @Override
    public void getDetailsSerieApi(int id) {
        getToken();
        ISerieService serieService = ServiceClient.createSerieService();
        Call<SerieDetailData> serieDetailExtrasCall = serieService.getSerieDetail("Bearer " + token, id);
        serieDetailExtrasCall.enqueue(new Callback<SerieDetailData>() {
            @Override
            public void onResponse(Call<SerieDetailData> call, Response<SerieDetailData> response) {
                Gson gson = new Gson();
                if (response.code() == 200) {
                    Log.e(TAG, ">>>>Response DetailsSerie>>>> " + gson.toJson(response.body().getData()));
                    serieDetail = response.body().getData();
                    imbdId = serieDetail.getImdbId();
                    if (imbdId.equals("")) {
                        iSerieDetailPresenter.showDetailsSerie(serieDetail, null);
                    } else {
                        IDetailsExtrasSerieService iDetailsExtrasSerieService = ServiceClient.createDetailsSerieService();
                        iDetailsExtrasSerieService
                                .getDetailsExtrasSerie(response.body().getData().getImdbId(), Constants.OMDB_APIKEY, Constants.PLOT)
                                .enqueue(new Callback<SerieDetailExtras>() {
                                    @Override
                                    public void onResponse(Call<SerieDetailExtras> call, Response<SerieDetailExtras> response) {
                                        if (response.code() == 200) {
                                            Log.e(TAG, ">>>>Response DetailsSerieExtras>>>>" + gson.toJson(response.body()));
                                            serieDetailExtras = response.body();
                                            iSerieDetailPresenter.showDetailsSerie(serieDetail, serieDetailExtras);
                                        } else {
                                            if (response.code() == 401) {
                                                iSerieDetailPresenter.showErrorDetails("Not authorized");

                                            } else {
                                                if (response.code() == 404) {
                                                    iSerieDetailPresenter.showErrorDetails("Not Found");
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SerieDetailExtras> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                    }

                } else {
                    if (response.code() == 401) {
                        iSerieDetailPresenter.showErrorDetails("Not authorized");

                    } else {
                        if (response.code() == 404) {
                            iSerieDetailPresenter.showErrorDetails("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SerieDetailData> call, Throwable t) {

            }
        });
    }

    private void getToken() {
        SharedPreferences mPrefs = ctx.getSharedPreferences(Constants.NAME_PREFS, Context.MODE_PRIVATE);
        token = mPrefs.getString("token", null);
    }
}
