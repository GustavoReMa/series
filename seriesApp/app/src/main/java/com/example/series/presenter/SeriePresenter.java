package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.ISerie;
import com.example.series.model.SerieInteractor;
import com.example.series.model.entity.Serie;

import java.util.List;

public class SeriePresenter implements ISerie.presenter {

    ISerie.view iSerieView;
    ISerie.model iSerieInteractor;
    Context ctx;

    public SeriePresenter(ISerie.view iSerieViev, Context ctx) {
        this.iSerieView = iSerieViev;
        this.ctx = ctx;
        iSerieInteractor = new SerieInteractor(this, ctx);
    }

    @Override
    public void showError(String error) {
        iSerieView.showError(error);
    }

    @Override
    public void getSeriesApi(String nameSerie) {
        iSerieInteractor.getSeriesApi(nameSerie);
    }

    @Override
    public void showSeries(List<Serie> series) {
        iSerieView.showSeries(series);
    }
}
