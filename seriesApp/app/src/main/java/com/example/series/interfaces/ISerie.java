package com.example.series.interfaces;

import com.example.series.model.entity.Serie;

import java.util.List;

public interface ISerie {

    interface model {
        //Interactor
        void getSeriesApi(String nameSerie);
    }

    interface presenter {
        //View
        void showError(String error);

        //Interactor
        void getSeriesApi(String nameSerie);

        //View
        void showSeries(List<Serie> series);
    }

    interface view {
        //View
        void showError(String error);

        //Presenter
        void getSeries(String nameSerie);

        //View
        void showSeries(List<Serie> series);
    }
}
