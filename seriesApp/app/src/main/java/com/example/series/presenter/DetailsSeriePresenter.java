package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.IDetailSerie;
import com.example.series.model.DetailsSerieInteractor;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;

public class DetailsSeriePresenter implements IDetailSerie.presenter {

    private IDetailSerie.view iSerieDetailView;
    private IDetailSerie.model iSerieDetailInteractor;
    private Context ctx;

    public DetailsSeriePresenter(IDetailSerie.view iSerieDetailView, Context ctx) {
        this.iSerieDetailView = iSerieDetailView;
        this.ctx = ctx;
        iSerieDetailInteractor = new DetailsSerieInteractor(this, this.ctx);
    }


    @Override
    public void showErrorDetails(String error) {
        iSerieDetailView.showErrorDetails(error);
    }

    @Override
    public void getDetailsSerieApi(int id) {
        iSerieDetailInteractor.getDetailsSerieApi(id);
    }

    @Override
    public void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras) {
        iSerieDetailView.showDetailsSerie(serieDetail, serieDetailExtras);
    }
}
