package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.ISerieDetail;
import com.example.series.model.SerieDetailsInteractor;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;

public class SerieDetailsPresenter implements ISerieDetail.presenter {

    ISerieDetail.view iSerieDetailView;
    ISerieDetail.model iSerieDetailInteractor;
    Context ctx;

    public SerieDetailsPresenter(ISerieDetail.view iSerieDetailView,Context ctx){
        this.iSerieDetailView = iSerieDetailView;
        this.ctx = ctx;
        iSerieDetailInteractor = new SerieDetailsInteractor(this,this.ctx);
    }


    @Override
    public void showError(String error) {
        iSerieDetailView.showError(error);
    }

    @Override
    public void getDetailsSerieApi(int id) {
        iSerieDetailInteractor.getDetailsSerieApi(id);
    }

    @Override
    public void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras) {
        iSerieDetailView.showDetailsSerie(serieDetail,serieDetailExtras);
    }
}
