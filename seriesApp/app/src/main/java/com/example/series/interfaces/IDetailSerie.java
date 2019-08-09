package com.example.series.interfaces;

import com.example.series.model.entity.Serie;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;

import java.util.List;

public interface IDetailSerie {

    interface model{
        void getDetailsSerieApi(int id);
    }
    interface presenter{
        //View
        void showErrorDetails(String error);

        //Interactor
        void getDetailsSerieApi(int id);

        //View
        void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras);

    }

    interface view{
        //View
        void showErrorDetails(String error);

        //Presenter
        void getSerieDetail(int id);

        //View
        void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras);
    }
}
